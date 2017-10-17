package com.teksystems.directoryreader;

import java.io.File;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.rules.ExpectedException;

import com.teksystems.directoryreader.DirectoryReaderUitl;

public class DirectoryReaderUtilTest {

	@Rule
	public ExpectedException expect = ExpectedException.none();
	private static final String DELIMETER = File.separator + ".";

	@Test
	public void NullPointerExceptionTest() {

		expect.expect(NullPointerException.class);
		File rootDirectory = new File("./testdirectory/Main Project");
		int directoryFileLength = rootDirectory.getAbsolutePath().length() - rootDirectory.getName().length();
		DirectoryReaderUitl directoryReaderUitl = new DirectoryReaderUitl(directoryFileLength);
		directoryReaderUitl.listAllFilesRecursively(null);
	}

	@Test
	public void sortFilesByExtensionsTest() {

		File rootDirectory = new File("./testdirectory/Main Project/Project 1");
		String listOfFileNames[] = rootDirectory.list();
		int directoryFileLength = rootDirectory.getAbsolutePath().length() - rootDirectory.getName().length();
		DirectoryReaderUitl directoryReaderUitl = new DirectoryReaderUitl(directoryFileLength);
		List<String> sortedListOfFiles = directoryReaderUitl.sortFilesByExtensions(listOfFileNames);
		String previous = null;
		for (String item : sortedListOfFiles) {
			if (item.contains(".")) {
				String[] split = item.split(DELIMETER);
				String current = "." + split[split.length - 1];
				if (previous == null) {
					previous = current;
				} else {
					assertTrue((current.compareTo(previous)) >= 0);
					previous = current;
				}
			}
		}
	}
}
