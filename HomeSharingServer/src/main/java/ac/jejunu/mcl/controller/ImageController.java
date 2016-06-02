package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.repository.FiledataRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by BK on 2016-06-03.
 */
@Controller
public class ImageController {

    @Autowired
    private FiledataRepository filedataRepository;

    private String directoryPath = "C:\\imageupload/";

    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") int id) throws IOException {
        String filename = filedataRepository.findOne(id).getFilename();
        RandomAccessFile f = new RandomAccessFile(directoryPath + filename, "r");
        byte[] b = new byte[(int) f.length()];
        f.readFully(b);
        final HttpHeaders headers = new HttpHeaders();

        String extension = FilenameUtils.getExtension(directoryPath + filename);
        if (extension.equals("jpg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        } else if (extension.equals("png")) {
            headers.setContentType(MediaType.IMAGE_PNG);
        }

        return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
    }

}
