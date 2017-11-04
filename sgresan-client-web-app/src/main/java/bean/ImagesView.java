package bean;
 
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 2; i++) {
            images.add("bannerlqh" + i + ".png");
        }
    }
 
    public List<String> getImages() {
        /*
        String pattern = "javax.faces.resource";
        String salida;
        salida.replaceAll(pattern, );*/
        System.out.print("get images 1: "+ images +" fin images1");
        return images;
    }
}