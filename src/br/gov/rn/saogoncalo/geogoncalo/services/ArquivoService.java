package br.gov.rn.saogoncalo.geogoncalo.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import br.gov.rn.saoconcalo.geogoncalo.models.Imagem;
import br.gov.rn.saoconcalo.geogoncalo.models.Obra;
import br.gov.rn.saogoncalo.geogoncalo.dao.ImagemDAO;

@Path("")
public class ArquivoService {
	
	private final String UPLOADED_FILE_PATH = "D:\\clayton/apache-tomcat-7.0.64/webapps/geo_goncalo/uploads/";
//	private final String UPLOADED_FILE_PATH = "F:\\dev/apache-tomcat-7.0.67/webapps/geo_goncalo/uploads/";
//	private final String UPLOADED_FILE_PATH = "/home/tomcat/webapps/portal_transito/ROOT/uploads/";
	
	@POST
	@Path("/arquivo/upload/{obraId}")
	@Produces("application/json")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input, @PathParam("obraId") long obraId) throws IOException {
		String fileName = "";
		int status = 500;
		
		Obra obra = new Obra();
		obra.setId(obraId);
		
		ImagemDAO imagemDAO = new ImagemDAO();
		
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
		List<InputPart> arquivoId = uploadForm.get("obraId");
		
		for (InputPart inputPart : inputParts) {
			try {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				fileName = getFileName(header);
				fileName = "img" + obraId + ".png";
				
				InputStream inputStream = inputPart.getBody(InputStream.class,null);
				byte [] bytes = IOUtils.toByteArray(inputStream);
				
				Imagem imagem = new Imagem();
				imagem.setNome(fileName);
				imagem.setObra(obra);
				
				System.out.println(imagem);
				
				if(writeFile(bytes, UPLOADED_FILE_PATH + fileName)){
					imagemDAO.inserirImagem(imagem);
					status = 200;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
		  	}
		}
		return Response.status(status).build();
	}
	
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
		
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
	

	private boolean writeFile(byte[] content, String filename) throws IOException {

		File file = new File(filename);
		System.out.println(content);
		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();

		if(file.exists())
			return true;
		
		return false;
	}
}
