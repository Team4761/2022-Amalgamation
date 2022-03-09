package frc.robot.LEDs;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.LEDs.Images.toCompress.CompressImages;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UpdateLEDCommand extends CommandBase {

    private File compressedImages = new File("C:\\Users\\User\\IdeaProjects\\LEDChickenSTRIPS\\src\\main\\java\\frc\\robot\\LEDs\\Images\\Compressed");
    private BufferedImage currentImage;

    private int count;
    private int width = 32;
    private int height = 16;
    private int RGBbutDumb;

    //Will be the list(s) of pixels that the LEDBuffer will use
    private int[][] imagePixels = new int[width*height][3];
    private AddressableLEDBuffer[] LEDList = new AddressableLEDBuffer[(int)compressedImages.length()];

    private double currentTime;
    private double updateTime = System.currentTimeMillis()+1000;

    //Image to be used:
    // Tell the robot which port to use for LEDs, so we can control them
    private AddressableLED LEDs = new AddressableLED(0);

    public UpdateLEDCommand() throws IOException {
        // each subsystem used by the command must be passed into the
        // addRequirements() method (which takes a vararg of Subsystem)
        addRequirements();
    }

    @Override
    public void initialize() {
        //Set the length of the LEDs (10*10 would mean a length of 100)
        LEDs.setLength(width*height);

        try {CompressImages.compressMyImage();}
        catch (IOException e) {e.printStackTrace();}

        count = 0;
        for (String directory : compressedImages.list()){
            try {
                currentImage = ImageIO.read(new File(directory));
                for(int y = 0; y < height; y++){
                    for(int x = 0; x < width; x++) {
                        RGBbutDumb = currentImage.getRGB(x, y);
                        int blue = RGBbutDumb & 0xff;
                        int green = (RGBbutDumb & 0xff00) >> 8;
                        int red = (RGBbutDumb & 0xff0000) >> 16;
                        imagePixels[x + (y * height)] = new int[]{red, green, blue};
                    }
                }
            } catch (IOException e) {e.printStackTrace();}
            for(int i = 0; i < height*width; i++){
                LEDList[count].setRGB(i,imagePixels[i][0],imagePixels[i][1],imagePixels[i][2]);
            }
            count++;
        }
        count = 0;
        //Tell the LEDs to start
        LEDs.setData(LEDList[0]);
        LEDs.start();
    }

    @Override
    public void execute() {
        //Goes through an input image array and gets each pixel
        currentTime = System.currentTimeMillis();
        if (updateTime <= currentTime){
            LEDs.setData(LEDList[count]);
            updateTime = currentTime+1000;
            count++;
            if (count>=LEDList.length){
                count = 0;
            }
        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        LEDs.stop();
    }
}
