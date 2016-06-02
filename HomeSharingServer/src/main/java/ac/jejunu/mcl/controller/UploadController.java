package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Filedata;
import ac.jejunu.mcl.repository.FiledataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by BK on 2016-06-03.
 */
@RestController
public class UploadController {

    @Autowired
    private FiledataRepository filedataRepository;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public Filedata file(@RequestParam("file") MultipartFile file, @RequestParam("filename") String filename) {
        System.currentTimeMillis();
        String uploadPath = "C:\\imageupload/";
        File newFile = null;
        String savedFileName =  String.valueOf(System.currentTimeMillis()) + filename;
        try {
            newFile = new File(uploadPath + savedFileName);
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Filedata filedata = new Filedata();
        filedata.setFilename(savedFileName);

        return filedataRepository.save(filedata);
    }


}
