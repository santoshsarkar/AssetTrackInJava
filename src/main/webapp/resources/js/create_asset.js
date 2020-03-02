$(document).ready(function() {
   var po=document.getElementById("input_j_idt13:purchaseOrder").value;  
   //alert(po);     

} );

function confirm(){
    alert("Confirm");
    
  var po=document.getElementById("input_j_idt13:purchaseOrder").value;  
  var trans_dt=document.getElementById("j_idt13:transDt_input").value;
  var acc_dt= document.getElementById("j_idt13:accountingDt_input").value;
  var descr= document.getElementById("input_j_idt13:descr").value;
  var descrshort= document.getElementById("input_j_idt13:descrshort").value;
  var vin= document.getElementById("input_j_idt13:vin").value;
  var serialId= document.getElementById("input_j_idt13:serialId").value;
  var quantity= document.getElementById("input_j_idt13:quantity").value;
  var cost= document.getElementById("input_j_idt13:cost").value;
  var inServiceDt_input=document.getElementById("j_idt13:inServiceDt_input").value;
  
  var asset_type=document.getElementById("j_idt13:asset").value;
  // alert(asset_type);
  

  
  document.getElementById("a_po").innerHTML = po;
  document.getElementById("a_trans_dt").innerHTML = trans_dt;
  document.getElementById("a_acc_dt").innerHTML = acc_dt;
  document.getElementById("a_inServiceDt_input").innerHTML = inServiceDt_input;
  document.getElementById("a_descr").innerHTML = descr;
  document.getElementById("a_serialId").innerHTML = serialId;
  document.getElementById("a_quantity").innerHTML = quantity;
  /*
  


  document.getElementById("a_cost").innerHTML = po;
  */
  
   // $('.modalPseudoClass').modal();
}

