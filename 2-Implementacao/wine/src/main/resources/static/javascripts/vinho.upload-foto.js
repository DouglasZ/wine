//NameSpace.
var Wine = Wine || {};

Wine.UploadFoto = (function() { 
	
	function UploadFoto() {
		this.uploadDrop = $('#upload-drop');
		this.containerFoto = $('.js-container-foto');
	}
	
	UploadFoto.prototype.iniciar = function() {
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			action: '/fotos/' + this.uploadDrop.data('codigo'),
			//Para ter acesso ao THIS precisamos usar o bind (para forçar o contexto de execução da função "onUploadCompleto",
			//ser o This que é o Uploadfoto sendo assim ele tem acesso ao constructor onde tem "uploadDrop" e o "containerFoto"  )
			complete: onUploadCompleto.bind(this),  
			beforeSend: adicionarCsrfToken
		};
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
	}
	
	//Requisição do javascript também precisamos passar o csrf
	function adicionarCsrfToken(xhr) {
		var header = $('input[name=_csrf_header]').val();
		var token = $('input[name=_csrf]').val();
		xhr.setRequestHeader(header, token);
	}
	
	function onUploadCompleto(foto) {
		this.uploadDrop.addClass('hidden');
		this.containerFoto.prepend('<img src="' + foto.url + '" class="img-responsive" style="margin: auto"/>');
	}
	
	return UploadFoto;
	
})();//Para executar a função é só abrir e fechar os ()

$(function() {
	
	var uploadFoto = new Wine.UploadFoto();
	uploadFoto.iniciar();
	
});
