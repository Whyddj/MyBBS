package com.wany.myuestcbbs.service;

import java.io.IOException;

public interface FileService {
    String upLoadSingleFile(byte[] content, String originFileName) throws IOException;
}
