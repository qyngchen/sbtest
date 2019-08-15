package com.qyngchen.sbminio.service;

import java.io.File;
import java.io.InputStream;

public interface MinioService {

    void uploadFile(File file);

    InputStream downloadFiel();
}
