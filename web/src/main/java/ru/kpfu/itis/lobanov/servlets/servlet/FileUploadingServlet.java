package ru.kpfu.itis.lobanov.servlets.servlet;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.lobanov.servlets.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "fileUpload", urlPatterns = "/upload")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class FileUploadingServlet extends HttpServlet {
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();
    public static final String FILE_PATH_PREFIX = "/tmp";
    public static final int DIRECTORIES_COUNT = 100;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/upload.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("file");

        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();

//        File file = new File(FILE_PATH_PREFIX + File.separator
//                + filename.hashCode() % DIRECTORIES_COUNT + File.separator + filename);
        InputStream content = part.getInputStream();
//        file.getParentFile().mkdirs();
//        file.createNewFile();


        File f = File.createTempFile(filename, null);
        FileOutputStream outputStream = new FileOutputStream(f);
        byte[] buffer = new byte[content.available()];
        Path p = f.toPath();

        content.read(buffer);

        outputStream.write(buffer);
        outputStream.close();

        Map map = cloudinary.uploader().upload(f, new HashMap<>());
//        Files.write(p, buffer, StandardOpenOption.DELETE_ON_CLOSE);
        System.out.println(map.get("url"));
        f.deleteOnExit();
    }
}
