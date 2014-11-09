package br.uff.tcc.bcc.esii.controlador;

import java.io.File;
import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import br.uff.tcc.bcc.esii.visao.GerenciadorDeTelas;

/**
 * Controlador responsável por realizar o intermédio entre o modelo e a visão.<P>
 * É um Singleton
 *
 */
public class ControladorTelaInicial  {
	
	private static ControladorTelaInicial controlador;
	
	private ControladorTelaInicial(){
		
	}
	
	public static ControladorTelaInicial getInstancia(){
		if (controlador == null){
			controlador = new ControladorTelaInicial();
		}
		return controlador;
	}
	
	/**
	 * Recebe um botão e identifica qual ação tomar quando este for apertado
	 * @param botao
	 */
	public void acao(Button botao){
    	if(botao.getId().equals("NOVO_JOGO")){
    		iniciaJogo();
    	}
	}
	
	private void iniciaJogo(){
		
	}

	public String obtemArquivo() {
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 String myDocuments = null;

		 try {
		     Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
		     p.waitFor();

		     InputStream in = p.getInputStream();
		     byte[] b = new byte[in.available()];
		     in.read(b);
		     in.close();

		     myDocuments = new String(b);
		     myDocuments = myDocuments.split("\\s\\s+")[4];
		     File diretorio = new File(myDocuments+"\\BatalhaDoPassinho");
		     diretorio.mkdirs();
		     diretorio.createNewFile();
			 fileChooser.setInitialDirectory(diretorio);
			 fileChooser.getExtensionFilters().addAll(
					 new ExtensionFilter("Text Files", "*.txt"));
			 File selectedFile = fileChooser.showOpenDialog(GerenciadorDeTelas.getInstancia().getStagePrincipal());
			 if (selectedFile != null) {
				 return  selectedFile.getAbsolutePath();  
			 }
			 return "";
		 } catch(Throwable t) {
		     t.printStackTrace();
		     return"";
		 }
		 
		 
	}
}
