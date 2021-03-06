package com.algaworks.wine.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.wine.model.TipoVinho;
import com.algaworks.wine.model.Vinho;
import com.algaworks.wine.repository.Vinhos;
import com.algaworks.wine.service.CadastroVinhoService;
import com.algaworks.wine.storage.FotoStorage;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	@Autowired
	private Vinhos vinhos;
	
	@Autowired
	private CadastroVinhoService cadastroVinhoService;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("/vinho/ListagemVinhos");
		mv.addObject("vinhos", vinhos.findAll());
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo( Vinho vinho ) {
		ModelAndView mv = new ModelAndView("/vinho/Cadastrovinho");
		mv.addObject("tipos", TipoVinho.values());
		return mv;
	}
	
	//@Valid 			 = Serve para validar os campos obrigatórios da classe
	//BindingResult 	 = Se tiver algum erro ele entrega no "result"
	//RedirectAttributes = Consulta nova no servidor pelo redirect para poder passar a mensagem
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Vinho vinho, BindingResult result, RedirectAttributes attributes ) {
		if( result.hasErrors() ){
			return novo(vinho);
		}
		cadastroVinhoService.salvar(vinho);
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso.");
		return new ModelAndView("redirect:/vinhos/novo");
	}
	
	//Se for o mesmo nome no request não precisa por no PathVariable
	//Usando o Objeto Vinho podemos usar o "codigo" para buscar pelo ID pois 
	//o JPA com spring MVC consegue identificar pelo o ID da classe
	@RequestMapping("/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") Vinho vinho){
		ModelAndView mv = new ModelAndView("/vinho/VisualizacaoVinho");
		
		if (vinho.temFoto()) {
			vinho.setUrl(fotoStorage.getUrl(vinho.getFoto()));
		}
		
		mv.addObject("vinho", vinho);
		return mv;
	}
}
