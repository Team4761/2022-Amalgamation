package frc.robot.LEDs.Images.toCompress;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class CompressImages {

    public static void compressMyImage() throws IOException {

        String[] oldPaths = new File("C:\\Users\\User\\IdeaProjects\\LEDChickenSTRIPS\\src\\main\\java\\frc\\robot\\LEDs\\Images\\toCompress").list();
        String[] tempPaths = new String[oldPaths.length];
        for(int x = 1; x <= oldPaths.length-1; x++){
            tempPaths[x-1] = oldPaths[x];
        }
        String[] paths = tempPaths;

        for(int i = (int) new File("C:\\Users\\User\\IdeaProjects\\LEDChickenSTRIPS\\src\\main\\java\\frc\\robot\\LEDs\\Images\\toCompress").length(); i > 1; i--) {
            File myimageissocool = new File(paths[i]);
            BufferedImage imageContents = ImageIO.read(myimageissocool);

            int verticalThiccness = imageContents.getHeight();
            int thicc = imageContents.getWidth();

            int desiredVerticalThiccness = 16;
            int desiredThicc = 32;

            float YRatio = verticalThiccness / desiredVerticalThiccness;
            float XRatio = thicc / desiredThicc;
            int RGB;

            BufferedImage image = new BufferedImage(desiredThicc, desiredVerticalThiccness, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y <= desiredVerticalThiccness - 1; y++) {
                for (int x = 0; x <= desiredThicc - 1; x++) {
                    RGB = imageContents.getRGB((int) (x * XRatio), (int) (y * YRatio));
                    image.setRGB(x, y, RGB);
                }
            }
            File outputFile = new File("C:\\Users\\User\\IdeaProjects\\LEDChickenSTRIPS\\src\\main\\java\\frc\\robot\\LEDs\\Images\\Compressed\\" + myimageissocool.getName());
            ImageIO.write(image, "png", outputFile);
        }
    }
}
