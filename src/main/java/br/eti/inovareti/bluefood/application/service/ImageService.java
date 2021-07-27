package br.eti.inovareti.bluefood.application.service;

import br.eti.inovareti.bluefood.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    @Value("${bluefood.files.logotipo}")
    private String logotipoDir;

    public void uploadLogotipo(MultipartFile file, String fileName) {
        try {
            IOUtils.copy(file.getInputStream(), fileName, logotipoDir);
        } catch (IOException e) {
            throw new ApplicationServiceException(e);
        }
    }
}
