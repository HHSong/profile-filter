import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Eric on 2/6/16.
 */
public class Filter {
    public void merge(String profile, String filter, String result) {
        try {
            // load source images
            BufferedImage image = ImageIO.read(new File(profile));
            BufferedImage overlay = ImageIO.read(new File(filter));

            // create the new image, canvas size is the max. of both image sizes
            int w = image.getWidth();
            int h = image.getHeight();
            Image tmp = overlay.getScaledInstance(w, h, Image.SCALE_SMOOTH);

            BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) combined.getGraphics();

            // paint both images, preserving the alpha channels
            g2d.drawImage(image, 0, 0, null);
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.4f));
            g2d.drawImage(tmp, 0, 0, null);
            // Save as new image
            ImageIO.write(combined, "PNG", new File(result));
        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        String img1 = "profile.jpg";
        String img2 = "filter.jpg";
        String out = "prayForAnything.png";
        Filter f = new Filter();
        f.merge(img1, img2, out);
    }
}
