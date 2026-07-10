package com;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileStore {

	public static final String UPLOAD_DIR = System.getProperty("java.io.tmpdir") + File.separator + "FrescoUploadedFiles";

	private static final List<UploadDetail> uploadedFiles = Collections.synchronizedList(new ArrayList<UploadDetail>());

	private FileStore() {
	}

	public static void addUploadedFile(UploadDetail detail) {
		uploadedFiles.add(detail);
	}

	public static List<UploadDetail> getUploadedFiles() {
		return uploadedFiles;
	}
}
