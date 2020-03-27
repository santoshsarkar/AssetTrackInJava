$(document).ready(function() {
    
  $( "#j_idt13:j_idt14_next" ).click(function() {
  alert( "Handler for .click() called." );
});
    
    //$('#j_idt12:j_idt14_wrapper') j_idt12:j_idt14
        

} );

$("j_idt13:j_idt14_next").click(function(){
    alert("Hi");
  });
function frm728(){
    
    /* Form728 */
    var today = new Date();
    var date = (today.getMonth()+1)+'/'+today.getDate()+'/'+today.getFullYear();
    document.getElementById('j_idt12:dateorderReceivingFromVendor_input').setAttribute("value",date );
    
    var porder=document.getElementById('j_idt12:po_input').value;
    document.getElementById('j_idt12:poNumber').value=porder;
    $( ".frm728" ).show();
    //var porder=document.getElementById('j_idt12:pos_label').textContent;
    //document.getElementById('j_idt12:poNumber').value=porder;
    
}
function callForm728(po,tag,asset){
    var poNo=po;
    var tagNo=tag;
    var asset_type=asset;
    //alert("Po: "+poNo+"Asst Type: "+asset_type);
    /*
    if(asset_type=="IT Hardware" || asset_type=="IT Software"  )
    { location.replace("/Assets/faces/itGoods/Create.xhtml?po="+poNo+"&tag="+tagNo) }
    else
    {location.replace("/Assets/faces/nonItGoods/Create.xhtml?po="+poNo+"&tag="+tagNo) }
    */
   {location.replace("/Assets/faces/itGoods/Form728.xhtml?po="+poNo) }
   
}

function wizComplete(){
    
   // var po=document.getElementById('j_idt13:output_po').value;
   
    PF('wiz').loadStep(PF('wiz').cfg.steps[0], true)
}

window.onload = function() {
    var today = new Date();
    var date = (today.getMonth()+1)+'/'+today.getDate()+'/'+today.getFullYear();
    //alert(date);
    document.getElementById('j_idt13:accountingDt_input').setAttribute("value",date );
    document.getElementById('j_idt13:transDt_input').setAttribute("value",date );
    document.getElementById('j_idt13:inServiceDt_input').setAttribute("value",date );
    document.getElementById('j_idt13:acquisitionDt_input').setAttribute("value",date );
    document.getElementById('j_idt13:locationEffdt_input').setAttribute("value",date );
    var fundcode='0648';
    document.getElementById('j_idt13:fundCode').setAttribute("value",fundcode );
    
    
    
    
  
    

  }
  
  
  
  function resetForm() {
 //alert("I am working!");
  document.getElementById("j_idt13:purchaseOrder").value = "";
  document.getElementById("j_idt13:transDt_input").value="";
  document.getElementById("j_idt13:accountingDt_input").value="";
  document.getElementById("j_idt13:descr").value="";
  document.getElementById("j_idt13:descrshort").value="";
  document.getElementById("j_idt13:vin").value="";
  document.getElementById("j_idt13:serialId").value="";
  document.getElementById("j_idt13:quantity").value="";
  document.getElementById("j_idt13:cost").value="";
  document.getElementById("j_idt13:inServiceDt_input").value="";
  document.getElementById("j_idt13:accountingDt_input").value="";
  document.getElementById("j_idt13:acquisitionDt_input").value="";
  document.getElementById("j_idt13:locationEffdt_input").value="";
  document.getElementById("j_idt13:fundCode").value="";
  document.getElementById("j_idt13:asset").value="";
  document.getElementById("j_idt13:asset_subtypeInner").value="";
  document.getElementById("j_idt13:asset_items").value="";
  

  
  
  
  
}