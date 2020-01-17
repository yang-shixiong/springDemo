package bean;

import org.springframework.web.multipart.MultipartFile;

public class File {
    private MultipartFile file;

    @Override
    public String toString() {
        return "File{" +
                "file=" + file +
                '}';
    }
}
