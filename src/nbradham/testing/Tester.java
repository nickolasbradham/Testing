package nbradham.testing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

final class Tester {

	public static void main(String[] args) throws IOException {
		ZipOutputStream zfos = new ZipOutputStream(new FileOutputStream("out.zip"));
		zfos.putNextEntry(new ZipEntry("test0.txt"));
		zfos.write("t0".getBytes());
		zfos.putNextEntry(new ZipEntry("test1.txt"));
		zfos.write("t1".getBytes());
		zfos.close();
	}
}