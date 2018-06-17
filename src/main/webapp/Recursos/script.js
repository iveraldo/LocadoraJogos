onload=function(){
  document.getElementsByTagName("button")[0].addEventListener("click", AdicionarJogos);
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
      
      var comboNomeJogos = document.getElementById("jogosSelecionados");
      
      //insere o id e o nome do jogo no combobox de jogos selecionados
      var option = document.createElement("option");
      option.innerHTML = linhaDeslocada.getElementsByTagName("td")[2].textContent;
      option.setAttribute("value", linhaDeslocada.getElementsByTagName("td")[1].textContent);
      
      comboNomeJogos.appendChild(option);
      
      //adiciona o valor do jogo ao valor do pedido
      valorPedido.value = parseFloat(valorPedido.value) + parseFloat(linhaDeslocada.getElementsByTagName("td")[3].textContent);
      
      //volta os contadores, pois o DOM foi modificado
      cont--;
      i--;
    }
  }
}