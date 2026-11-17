import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BuildV7BgAndV6Featured {
    public static void main(String[] args) throws Exception {
        BufferedImage ref = ImageIO.read(new File("public/assets/aistudio/Mor_Neon_Hik_yeler_Uygulamas__a101f8f1.png"));
        int refW = ref.getWidth();
        int refH = ref.getHeight();

        // =========================================================================================
        // PART 1: Build img_aiden_modal_true_bg_v7.jpg
        // "Menünün direkt arka planının tasarımdaki şey varya daha morumsu olsun ama koyuda olmasın"
        // =========================================================================================
        BufferedImage bgRaw = new BufferedImage(refW, refH, BufferedImage.TYPE_INT_RGB);

        // In ref, the richest purple-violet lightning clouds & nebula smoke are in:
        // 1) Upper lightning cloud patch: x = 365..472, y = 10..128
        // 2) Right-side glowing purple smoke trail: x = 545..810, y = 578..612 and y = 980..1010
        for (int y = 0; y < refH; y++) {
            float ny = y / (float) (refH - 1);
            int wave = (int) Math.round(11.0 * (1.0 - Math.cos(2.0 * Math.PI * y / 210.0)));
            for (int x = 0; x < refW; x++) {
                float nx = x / (float) (refW - 1);

                // Sample the vivid purple smoke from x = 510..805 (avoiding the dark left side x < 480 so it's never dark!)
                float uSmoke1 = (float) (0.5 * (1.0 - Math.cos(2.0 * Math.PI * (nx * 0.75 + ny * 0.42))));
                float uSmoke2 = (float) (0.5 * (1.0 - Math.cos(2.0 * Math.PI * ((1.0 - nx) * 0.80 + ny * 0.55 + 0.25))));
                int sx1 = 505 + Math.round(uSmoke1 * 295f);
                int sx2 = 505 + Math.round(uSmoke2 * 295f);

                int cSmoke1 = ref.getRGB(sx1, 582 + wave);
                int cSmoke2 = ref.getRGB(sx2, 983 + wave);

                // Sample the bright purple-magenta lightning sky patch (x = 368..470, y = 10..126)
                float uCloud = (float) (0.5 * (1.0 - Math.cos(2.0 * Math.PI * (nx * 0.90 + ny * 0.38))));
                float vCloud = (float) (0.5 * (1.0 - Math.cos(2.0 * Math.PI * (ny * 1.45 - nx * 0.28))));
                int cx = 368 + Math.round(uCloud * 100f);
                int cy = 10 + Math.round(vCloud * 114f);
                int cCloud = ref.getRGB(cx, cy);

                float s = (float) (0.5 * (1.0 - Math.cos(Math.PI * ny)));
                int baseSmoke = lerpRgb(cSmoke1, cSmoke2, s);

                // Blend strongly with the vivid purple lightning cloud texture (0.42..0.78)
                double plume1 = Math.exp(-Math.pow((nx - (0.74 - 0.35 * ny)) / 0.36, 2));
                double plume2 = Math.exp(-Math.pow((nx - (0.26 + 0.40 * ny)) / 0.38, 2));
                double plume3 = Math.exp(-Math.pow((nx - 0.50) / 0.42, 2)) * (0.65 + 0.35 * Math.sin(ny * Math.PI * 2.5));
                float cloudMix = (float) Math.min(0.78, 0.38 + 0.40 * Math.max(plume3, Math.max(plume1, plume2)));

                int mixed = lerpRgb(baseSmoke, cCloud, cloudMix);
                int r = (mixed >> 16) & 0xFF;
                int g = (mixed >> 8) & 0xFF;
                int b = mixed & 0xFF;

                // Ensure the entire background is distinctly MORE PURPLE ("daha morumsu") and NEVER dark ("koyu da olmasın"):
                // Lift shadows to a rich royal amethyst (#351268) and boost midtones toward vibrant violet-orchid (#7E22CE / #A855F7)
                float normR = r / 255f;
                float normG = g / 255f;
                float normB = b / 255f;

                // Gamma lift (< 1.0 brightens shadows & midtones without blowing out highlights)
                int outR = Math.min(240, Math.round(48f + (float) Math.pow(normR, 0.76) * 168f));
                int outG = Math.min(165, Math.round(15f + (float) Math.pow(normG, 0.82) * 105f));
                int outB = Math.min(255, Math.round(92f + (float) Math.pow(normB, 0.72) * 158f));

                bgRaw.setRGB(x, y, (outR << 16) | (outG << 8) | outB);
            }
        }

        // Smooth 9x9 pass for silky photographic nebula quality
        BufferedImage bgSmooth = new BufferedImage(refW, refH, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < refH; y++) {
            for (int x = 0; x < refW; x++) {
                int sr = 0, sg = 0, sb = 0, cnt = 0;
                for (int dy = -6; dy <= 6; dy += 2) {
                    int sy = Math.max(0, Math.min(refH - 1, y + dy));
                    for (int dx = -6; dx <= 6; dx += 2) {
                        int sx = Math.max(0, Math.min(refW - 1, x + dx));
                        int rgb = bgRaw.getRGB(sx, sy);
                        sr += (rgb >> 16) & 0xFF;
                        sg += (rgb >> 8) & 0xFF;
                        sb += rgb & 0xFF;
                        cnt++;
                    }
                }
                bgSmooth.setRGB(x, y, ((sr / cnt) << 16) | ((sg / cnt) << 8) | (sb / cnt));
            }
        }
        ImageIO.write(bgSmooth, "jpg", new File("app/src/main/res/drawable/img_aiden_modal_true_bg_v7.jpg"));
        System.out.println("Saved img_aiden_modal_true_bg_v7.jpg successfully!");

        // =========================================================================================
        // PART 2: Build img_aiden_modal_featured_clean_v6.png
        // "Öne çıkan evrendeki resimin geçişi harbi çok sağlam bişey olsun resmi sağ tarafını daha fazla göster diyorum."
        // =========================================================================================
        BufferedImage orig = ImageIO.read(new File("public/assets/aistudio/tmp_ref_featured_interior.png"));
        int W = orig.getWidth();   // 773
        int H = orig.getHeight();  // 300

        // Step 2A: Reconstruct the extended right side of Aiden's portrait (srcX = 365..435)
        // In orig, at srcX = 371 there is a vertical step where the right dark overlay darkened Aiden's outer red sleeve
        // (srcX = 371..425, y = 148..299) and the stadium bowl (srcX = 371..425, y = 0..148).
        // Let's un-darken srcX = 368..435 by matching the luminance & color continuation from srcX = 358..367,
        // so Aiden's portrait has ZERO vertical line at 371 and extends naturally all the way to srcX = 435!
        BufferedImage extendedPortrait = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                extendedPortrait.setRGB(x, y, orig.getRGB(x, y));
            }
        }

        for (int y = 0; y < H; y++) {
            int sy = Math.max(2, Math.min(H - 3, y));
            // Measure average RGB just to the left of the seam (srcX = 360..366) and just to the right (srcX = 374..380)
            float rLeft = 0, gLeft = 0, bLeft = 0;
            float rRight = 0, gRight = 0, bRight = 0;
            for (int d = -3; d <= 3; d++) {
                int cy = Math.max(2, Math.min(H - 3, sy + d));
                for (int sx = 360; sx <= 366; sx++) {
                    int c = orig.getRGB(sx, cy);
                    rLeft += (c >> 16) & 0xFF;
                    gLeft += (c >> 8) & 0xFF;
                    bLeft += c & 0xFF;
                }
                for (int sx = 374; sx <= 380; sx++) {
                    int c = orig.getRGB(sx, cy);
                    rRight += (c >> 16) & 0xFF;
                    gRight += (c >> 8) & 0xFF;
                    bRight += c & 0xFF;
                }
            }
            rLeft /= 49f; gLeft /= 49f; bLeft /= 49f;
            rRight = Math.max(8f, rRight / 49f);
            gRight = Math.max(4f, gRight / 49f);
            bRight = Math.max(12f, bRight / 49f);

            // Gain factors that restore the darkened right-of-371 pixels to match the left-of-368 portrait brightness!
            float gainR = Math.min(3.4f, Math.max(1.0f, rLeft / rRight));
            float gainG = Math.min(3.2f, Math.max(1.0f, gLeft / gRight));
            float gainB = Math.min(2.8f, Math.max(1.0f, bLeft / bRight));

            for (int x = 366; x <= 445; x++) {
                int c = orig.getRGB(x, sy);
                int r = (c >> 16) & 0xFF;
                int g = (c >> 8) & 0xFF;
                int b = c & 0xFF;

                // Ramp the gain across x = 366..374 to bridge the 371 boundary seamlessly,
                // and also blend with the natural continuation of Aiden's shoulder curve & stadium lights!
                float ramp = Math.min(1f, Math.max(0f, (x - 366f) / 7f));
                float effGainR = 1f + (gainR - 1f) * ramp;
                float effGainG = 1f + (gainG - 1f) * ramp;
                float effGainB = 1f + (gainB - 1f) * ramp;

                int restR = Math.min(255, Math.round(r * effGainR));
                int restG = Math.min(255, Math.round(g * effGainG));
                int restB = Math.min(255, Math.round(b * effGainB));
                int restoredRgb = (0xFF << 24) | (restR << 16) | (restG << 8) | restB;

                // Also sample the natural slope continuation from srcX = 355..365 (sloping downward slightly for the shoulder)
                int slopeY = Math.min(H - 3, Math.max(2, Math.round(sy - (x - 365) * (sy > 140 ? 0.38f : -0.12f))));
                int mirrorX = Math.max(320, 365 - (x - 365) / 2);
                int contRgb = orig.getRGB(mirrorX, slopeY);

                // Blend restored underlying pixels with the smooth shoulder/stadium continuation so there is zero noise
                float contMix = Math.min(0.65f, 0.25f + (x - 366f) / 110f);
                extendedPortrait.setRGB(x, y, lerpRgb(restoredRgb, contRgb, contMix));
            }
        }

        // Smooth the seam zone x = 360..410 horizontally with a 9px kernel so the 371 line is 100% eradicated!
        BufferedImage seamlessPortrait = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (x >= 356 && x <= 440) {
                    int sr = 0, sg = 0, sb = 0, cnt = 0;
                    // Progressive depth-of-field blur as x increases from 345 to 440!
                    int radX = 2 + Math.max(0, (x - 350) / 8);
                    int radY = 1 + Math.max(0, (x - 355) / 12);
                    for (int dy = -radY; dy <= radY; dy++) {
                        int ny = Math.max(2, Math.min(H - 3, y + dy));
                        for (int dx = -radX; dx <= radX; dx++) {
                            int nx = Math.max(2, Math.min(445, x + dx));
                            int rgb = extendedPortrait.getRGB(nx, ny);
                            sr += (rgb >> 16) & 0xFF;
                            sg += (rgb >> 8) & 0xFF;
                            sb += rgb & 0xFF;
                            cnt++;
                        }
                    }
                    seamlessPortrait.setRGB(x, y, (0xFF << 24) | ((sr / cnt) << 16) | ((sg / cnt) << 8) | (sb / cnt));
                } else {
                    seamlessPortrait.setRGB(x, y, extendedPortrait.getRGB(x, y));
                }
            }
        }

        // Step 2B: Prepare cleanCastle (same artifact-free castle + purple lightning clouds on the right)
        BufferedImage cleanCastle = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                cleanCastle.setRGB(x, y, orig.getRGB(x, y));
            }
        }
        for (int x = 520; x <= 634; x++) {
            int cTopCloud = orig.getRGB(Math.max(560, x), 56);
            int cDarkMid = 0xFF1A0836;
            int cDarkBot = 0xFF14052A;
            for (int y = 58; y < H; y++) {
                if (y <= 150) {
                    float t = (y - 58f) / (150f - 58f);
                    float s = quintic(t);
                    cleanCastle.setRGB(x, y, lerpRgb(cTopCloud, cDarkMid, s));
                } else {
                    float t = (y - 150f) / (H - 1f - 150f);
                    cleanCastle.setRGB(x, y, lerpRgb(cDarkMid, cDarkBot, t));
                }
            }
        }
        for (int x = 635; x <= 692; x++) {
            int cTop = orig.getRGB(x, 140);
            int cBot = 0xFF180732;
            for (int y = 142; y <= 225; y++) {
                float t = (y - 142f) / (225f - 142f);
                cleanCastle.setRGB(x, y, lerpRgb(cTop, cBot, quintic(t)));
            }
        }
        for (int x = 635; x < W; x++) {
            int sumR = 0, sumG = 0, sumB = 0, cnt = 0;
            for (int dx = -8; dx <= 8; dx++) {
                int sx = Math.max(635, Math.min(W - 6, x + dx));
                int rgb = cleanCastle.getRGB(sx, 210);
                sumR += (rgb >> 16) & 0xFF;
                sumG += (rgb >> 8) & 0xFF;
                sumB += rgb & 0xFF;
                cnt++;
            }
            int cSmoothedTop = (0xFF << 24) | ((sumR / cnt) << 16) | ((sumG / cnt) << 8) | (sumB / cnt);
            int cDarkBot = 0xFF16062E;
            for (int y = 212; y < H; y++) {
                float t = (y - 212f) / (H - 1f - 212f);
                cleanCastle.setRGB(x, y, lerpRgb(cSmoothedTop, cDarkBot, quintic(t)));
            }
        }

        // Step 2C: Assemble Final 773x300 RGBA PNG (img_aiden_modal_featured_clean_v6.png):
        // - Shift portrait left by 18px so Aiden's left shoulder is framed AND we show MUCH MORE of the right side:
        //   100% full portrait clarity all the way to x = 338 (and up to x = 348 on his red shoulder!),
        //   with a wide, contour-hugging 82px quintic + atmospheric violet mist transition from x = 328 to x = 410!
        BufferedImage finalImg = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        BufferedImage previewOpaque = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
        int leftShift = 18;

        for (int y = 0; y < H; y++) {
            float ny = y / (float) (H - 1);
            int srcY = Math.max(2, Math.min(H - 3, y));

            // Contour-aware transition start & end so Aiden's right shoulder (ny = 0.50..1.0) and upper stadium lights (ny = 0.0..0.45)
            // show their full right-side detail and dissolve along a natural organic curve rather than a rigid vertical ruler line!
            float shoulderCurve = (float) (12.0 * Math.sin(ny * Math.PI * 0.95));
            float fadeStart = 328f + shoulderCurve; // ~328..340 (full 100% portrait visibility up to here!)
            float fadeEnd = 410f + shoulderCurve * 0.6f; // ~410..417 (wide ~82px cinematic quintic transition!)

            for (int x = 0; x < W; x++) {
                float nx = x / (float) (W - 1);

                // Rich dark-violet glass field in the center (#180834 -> #240C48)
                float cloudGlow = (float) Math.max(0.0, Math.exp(-Math.pow((nx - 0.72) / 0.24, 2) - Math.pow((ny - 0.26) / 0.44, 2)));
                int glassR = Math.round(22 + 28 * cloudGlow);
                int glassG = Math.round(7 + 10 * cloudGlow);
                int glassB = Math.round(45 + 48 * cloudGlow);
                int glassRgb = (0xFF << 24) | (glassR << 16) | (glassG << 8) | glassB;

                // Portrait sample from our extended, seam-free portrait (allows sampling all the way to srcX = 435!)
                int pSrcX = Math.max(2, Math.min(435, x + leftShift));
                int portraitRgb = seamlessPortrait.getRGB(pSrcX, srcY);

                // Quintic C2-continuous transition weight + subtle purple-magenta atmospheric light-wrap in the mid-transition!
                float portraitWeight;
                if (x <= fadeStart) {
                    portraitWeight = 1.0f;
                } else if (x >= fadeEnd) {
                    portraitWeight = 0.0f;
                } else {
                    float u = (x - fadeStart) / (fadeEnd - fadeStart);
                    portraitWeight = 1.0f - quintic(u);
                }

                // In the transition zone (0.05 < portraitWeight < 0.95), add a subtle violet atmospheric light-wrap
                // so the warm red sleeve and stadium lights melt into purple mist rather than simply darkening
                if (portraitWeight > 0.01f && portraitWeight < 0.99f) {
                    float mistBell = 4f * portraitWeight * (1f - portraitWeight); // peaks at 1.0 in middle of transition
                    int mistRgb = 0xFF341060; // Rich violet atmospheric mist
                    portraitRgb = lerpRgb(portraitRgb, mistRgb, mistBell * 0.28f);
                }

                // Right-side Castle & Purple Clouds (x = 455..772)
                float castleWeight;
                int castleRgb = glassRgb;
                if (x <= 455) {
                    castleWeight = 0.0f;
                } else {
                    float cNorm = (x - 455f) / (W - 1f - 455f);
                    int cSrcX = Math.max(555, Math.min(W - 3, Math.round(555f + cNorm * (W - 3f - 555f))));
                    castleRgb = cleanCastle.getRGB(cSrcX, srcY);

                    if (x >= 605) {
                        castleWeight = 1.0f;
                    } else {
                        float u = (x - 455f) / (605f - 455f);
                        castleWeight = quintic(u);
                    }
                }

                int combined = glassRgb;
                if (portraitWeight > 0f) {
                    combined = lerpRgb(combined, portraitRgb, portraitWeight);
                }
                if (castleWeight > 0f) {
                    combined = lerpRgb(combined, castleRgb, castleWeight);
                }

                float solidFactor = Math.max(portraitWeight, castleWeight * 0.90f);
                float alpha = 0.56f + 0.44f * solidFactor;
                int a = Math.max(0, Math.min(255, Math.round(alpha * 255f)));

                finalImg.setRGB(x, y, (a << 24) | (combined & 0x00FFFFFF));
                previewOpaque.setRGB(x, y, 0xFF000000 | (combined & 0x00FFFFFF));
            }
        }

        ImageIO.write(finalImg, "png", new File("app/src/main/res/drawable/img_aiden_modal_featured_clean_v6.png"));
        ImageIO.write(previewOpaque, "png", new File("public/assets/aistudio/tmp_preview_featured_v6.png"));
        System.out.println("Saved img_aiden_modal_featured_clean_v6.png successfully!");
    }

    // Perlin's C2-continuous quintic smoothstep: 6u^5 - 15u^4 + 10u^3 (zero 1st and 2nd derivatives at u=0 and u=1)
    private static float quintic(float u) {
        u = Math.max(0f, Math.min(1f, u));
        return u * u * u * (u * (u * 6f - 15f) + 10f);
    }

    private static int lerpRgb(int c1, int c2, float t) {
        t = Math.max(0f, Math.min(1f, t));
        int r1 = (c1 >> 16) & 0xFF, g1 = (c1 >> 8) & 0xFF, b1 = c1 & 0xFF;
        int r2 = (c2 >> 16) & 0xFF, g2 = (c2 >> 8) & 0xFF, b2 = c2 & 0xFF;
        int r = Math.round(r1 + (r2 - r1) * t);
        int g = Math.round(g1 + (g2 - g1) * t);
        int b = Math.round(b1 + (b2 - b1) * t);
        return (0xFF << 24) | (r << 16) | (g << 8) | b;
    }
}
