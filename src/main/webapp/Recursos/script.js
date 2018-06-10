onload=function(){
  document.getElementsByTagName("button")[0].addEventListener("click", AdicionarJogos);
  document.getElementsByTagName("button")[1].addEventListener("click", RemoverJogos);
}
                   
function AdicionarJogos(){
  var tbody = document.getElementsByTagName("tbody")[0];
  var trs = tbody.getElementsByTagName("tr");
  var cont = trs.length;
  var valorPedido = document.getElementById("valorPedido");
  
  for (var i = 0; i < cont; i++) {
    var checkbox = trs[i].getElementsByTagName("input")[0];
    
    if(checkbox.checked){
      //recupera a linha a ser deslocada
      var linhaDeslocada = checkbox.parentNode.parentNode;
     
      //remove a linha
      tbody.removeChild(linhaDeslocada);
      
      //desmarca o checkbox
      checkbox.checked = false;
      
      //esconde a celula de quantidade disponivel
      linhaDeslocada.getElementsByTagName("td")[4].classList.add("d-none");
      
      //adiciona o valor do jogo ao valor do pedido
      valorPedido.value = parseFloat(valorPedido.value) + parseFloat(linhaDeslocada.getElementsByTagName("td")[3].textContent);
      
      //reinsere na tabela nova
      document.getElementsByTagName("tbody")[1].appendChild(linhaDeslocada);
      
      //volta os contadores, pois o DOM foi modificado
      cont--;
      i--;
    }
  }
}
  
  function RemoverJogos(){
  var tbody = document.getElementsByTagName("tbody")[1];
  var trs = tbody.getElementsByTagName("tr");
  var cont = trs.length;
  
  for (var i = 0; i < cont; i++) {
    var checkbox = trs[i].getElementsByTagName("input")[0];
    
    if(checkbox.checked){
      //recupera a linha a ser deslocada
      var linhaDeslocada = checkbox.parentNode.parentNode;
     
      //remove a linha
      tbody.removeChild(linhaDeslocada);
      
      //desmarca o checkbox
      checkbox.checked = false;
      
      //esconde a celula de quantidade disponivel
      linhaDeslocada.getElementsByTagName("td")[4].classList.remove("d-none");
      
      //substrai o valor do jogo do valor do pedido
      valorPedido.value = parseFloat(valorPedido.value) - parseFloat(linhaDeslocada.getElementsByTagName("td")[3].textContent);
      
      //reinsere na tabela nova
      document.getElementsByTagName("tbody")[0].appendChild(linhaDeslocada);
      
      //volta os contadores, pois o DOM foi modificado
      cont--;
      i--;
    }
  }
}