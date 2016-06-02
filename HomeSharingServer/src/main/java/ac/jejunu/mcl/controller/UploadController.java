package ac.jejunu.mcl.controller;

import ac.jejunu.mcl.entity.Filedata;
import ac.jejunu.mcl.repository.FiledataRepository;
import ac.jejunu.mcl.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private HomeRepository homeRepository;

    @RequestMapping(path = "/imageupload/home/{id}", method = RequestMethod.POST)
    public Filedata file(@RequestParam("file") MultipartFile file, @RequestParam("filename") String filename, @PathVariable("id") int homeId) {
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

        int imageId = filedataRepository.save(filedata).getId();
        homeRepository.updateImage(homeId, imageId);

        return filedataRepository.findOne(imageId);
    }


}
