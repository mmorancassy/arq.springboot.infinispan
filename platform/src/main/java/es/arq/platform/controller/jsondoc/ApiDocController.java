package es.arq.platform.controller.jsondoc;

import java.util.List;

import org.jsondoc.core.pojo.JSONDoc;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.core.scanner.JSONDocScanner;
import org.jsondoc.springmvc.scanner.Spring4JSONDocScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiDocController {
	
	@Value("${jsondoc.version}")
	private String version;
	
	@Value("${jsondoc.basePath}")
	private String basePath;
	
	@Value("#{'${jsondoc.packages}'.split(',')}")
	private List<String> packages;
	
	private JSONDocScanner jsondocScanner;
	
	private boolean playgroundEnabled = true;

	private MethodDisplay displayMethodAs = MethodDisplay.URI;
	
	@RequestMapping(value = "/api")
	public String index() {
		return "rest.html";
	}	

	@RequestMapping(value = "/apidoc", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JSONDoc getApi() {
		jsondocScanner = new Spring4JSONDocScanner();
		return jsondocScanner.getJSONDoc(version, basePath, packages, playgroundEnabled, displayMethodAs);
	}

}
