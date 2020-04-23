window.onload = function() {
    var today = new Date();
    var date = (today.getMonth()+1)+'/'+today.getDate()+'/'+today.getFullYear();
    //alert(date);
    var tran_dt=document.getElementById('j_idt13:transDt_input').value.length;
    var acc_dt=document.getElementById('j_idt13:accountingDt_input').value.length;
    var inserv_dt=document.getElementById('j_idt13:inServiceDt_input').value.length;
    var acq_dt=document.getElementById('j_idt13:acquisitionDt_input').value.length;
    var loceff_dt=document.getElementById('j_idt13:locationEffdt_input').value.length;
    
    //alert("TDATE: "+tran_dt);
    if (tran_dt==0){document.getElementById('j_idt13:transDt_input').setAttribute("value",date );} 
    if (acc_dt==0){document.getElementById('j_idt13:accountingDt_input').setAttribute("value",date );} 
    if (inserv_dt==0){document.getElementById('j_idt13:inServiceDt_input').setAttribute("value",date );} 
    if (acq_dt==0){document.getElementById('j_idt13:acquisitionDt_input').setAttribute("value",date );} 
    if (loceff_dt==0){document.getElementById('j_idt13:locationEffdt_input').setAttribute("value",date );} 
    
    
   // document.getElementById('j_idt13:accountingDt_input').setAttribute("value",date );
    //document.getElementById('j_idt13:transDt_input').setAttribute("value",date );
   // document.getElementById('j_idt13:inServiceDt_input').setAttribute("value",date );
  //  document.getElementById('j_idt13:acquisitionDt_input').setAttribute("value",date );
  //  document.getElementById('j_idt13:locationEffdt_input').setAttribute("value",date );
    
    document.getElementById('j_idt12:dateorderReceivingFromVendor_input').setAttribute("value",date );
    document.getElementById('j_idt12:dateorderReceivingFromContractor_input').setAttribute("value",date );
    
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
  document.getElementById("j_idt13:asset_subtypeInner").value="";
 
  
  
  
}

function frm728(){
    /* Form728 */
    var today = new Date();
    var date = (today.getMonth()+1)+'/'+today.getDate()+'/'+today.getFullYear();
    document.getElementById('j_idt12:dateorderReceivingFromVendor_input').setAttribute("value",date );
    /*
    document.getElementById('j_idt12:dateorderReceivingFromContractor_input').setAttribute("value",date );
    document.getElementById('j_idt12:dateorderreceivingfromBSO_input').setAttribute("value",date );
    */
    
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
    alert("Po: "+poNo+"Asst Type: "+asset_type);
    
    if(asset_type=="010" || asset_type=="020"  )
    { location.replace("/Assets/faces/itGoods/Form728.xhtml?po="+poNo+"&tag="+tagNo) }
    else
    {location.replace("/Assets/faces/nonItGoods/Form728.xhtml?po="+poNo+"&tag="+tagNo) }
    
   // {location.replace("/Assets/faces/itGoods/Form728.xhtml?po="+poNo) }
   
}

function wizComplete(){
    
   // var po=document.getElementById('j_idt13:output_po').value;
   
    PF('wiz').loadStep(PF('wiz').cfg.steps[0], true)
}

function printAssets(tag) {
    const Http = new XMLHttpRequest();
    const url = "http://localhost/Assets/api/AssetAPIController/action?code="+tag;
    console.log(url);
    Http.open("GET", url);
    Http.send();

    Http.onreadystatechange = (e) => {
        console.log(Http.responseText)
    }
}

function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}