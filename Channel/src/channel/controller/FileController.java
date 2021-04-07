package channel.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileController() {
    	
    }
    	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		 // 이미지 업로드할 경로
		String saveDirectory = request.getSession().getServletContext().getRealPath("resources/image/upload");
		
		// 파일 저장 경로
				File file = new File(saveDirectory);
				try {
					if (!file.exists()) {
						file.mkdirs();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				System.out.println(saveDirectory);
		
	    int size = 10 * 1024 * 1024;  // 업로드 사이즈 제한 10M 이하
		
	    PrintWriter out = response.getWriter();
	    
		
	        // 파일업로드 및 업로드 후 파일명 가져옴
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			String formName = (String)files.nextElement();
			String fileName = multi.getFilesystemName(formName);
			
			if (fileName == null) {
				// 파일이 업로드 되지 않았을때 
				out.print("파일 업로드 되지 않았음"); 
			} else {
				// 업로드된 경로와 파일명을 통해 이미지의 경로를 생성
				String uploadPath = "resources/image/upload/" + fileName;
				System.out.println("resources/image/upload/" + fileName);
				
				// 생성된 경로를 JSON 형식으로 보내주기 위한 설정
				JSONObject jobj = new JSONObject();
				jobj.put("url", uploadPath);
				jobj.put("fileName", fileName);
				
				response.setContentType("application/json"); // 데이터 타입을 json으로 설정하기 위한 세팅
				out.print(jobj.toJSONString());
			}
	}

}
