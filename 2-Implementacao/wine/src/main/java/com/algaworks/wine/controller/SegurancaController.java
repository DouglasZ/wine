package com.algaworks.wine.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SegurancaController {

	//@AuthenticationPrincipal = Recebo o usuário logado no sistema.
	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		//Verifica se o user estiver logado direcionamos para a página de novo, para não apresenta tela de login de novo.
		if (user != null) {
			return "redirect:/vinhos/novo";
		}
		
		return "Login";
	}
}
