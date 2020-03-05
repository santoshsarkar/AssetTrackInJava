$(document).ready(function() {
    //alert("ss");
    //$('#j_idt12:j_idt14_wrapper') j_idt12:j_idt14
        

} );

function callForm728(po,tag,asset){
    var poNo=po;
    var tagNo=tag;
    var asset_type=asset;
    //alert("Po: "+poNo+"Asst Type: "+asset_type);
    if(asset_type=="IT Hardware" || asset_type=="IT Software"  )
    { location.replace("/Form728/faces/itGoods/Create.xhtml?po="+poNo+"&tag="+tagNo) }
    else
    {location.replace("/Form728/faces/nonItGoods/Create.xhtml?po="+poNo+"&tag="+tagNo) }
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