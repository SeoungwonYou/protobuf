import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.protobuff.Application;
import com.sample.protobuff.mapper.SampleMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
@MybatisTest
public class ProtobufMessagesTest {

	@Autowired
	private static SampleMapper sampleMapper;
	
	@Test
	public void streamingMultipleMessagesTest() throws Exception {
		
		String currentClassBinpath = System.getProperty("user.dir").concat(File.separator)
				.concat("src").concat(File.separator)
				.concat("test").concat(File.separator)
				.concat("resources").concat(File.separator);
		
		int size = sampleMapper.selectList().size();
		System.out.println(">>>>>>>>>" + size);
		assertEquals(size, 5);
	}
	
	
	public abstract class BinWritter{
		
		public abstract void writeElement(FileOutputStream fos);
		
		public void write(String testFilePath){
			
			System.out.println(testFilePath);
			File binFile = new File(testFilePath);
			
			try(FileOutputStream fos = new FileOutputStream(binFile)){
				
				writeElement(fos);
				
			}catch(Exception e){
				e.printStackTrace();
			}
						
		}
	}
}
