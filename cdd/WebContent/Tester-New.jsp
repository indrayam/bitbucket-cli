<%@ page  language="java" session="true" import="java.net.InetAddress" %>
<%
 String hostName=java.net.InetAddress.getLocalHost().getHostName();
 String logDir = System.getProperty("user.dir");
 String user = System.getProperty("user.name");
 String jvm = user.replaceFirst("xx","");
%>

<HTML>
<BODY>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>COMPONENT TEST</title>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript"> 
var xmlhttp=false;
 
function post2page(url,docForm,destDiv ) {
	document.getElementById(destDiv).innerHTML = '<p class="blinkytext"> Pl. wait..loading result.....  </p>';
   try{
      // Opera 8.0+, Firefox, Safari
      xmlhttp = new XMLHttpRequest();
   } catch (e){
      // Internet Explorer Browsers
      try{
         xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
      } catch (e) {
         try{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
         } catch (e){
            // Something went wrong
            alert("Your browser broke!");
            return false;
         }
      }
   }
   var params = formData2QueryString(docForm);
   xmlhttp.open("POST",url, true);
   xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlhttp.setRequestHeader("Content-length", params.length);
   xmlhttp.setRequestHeader("Connection", "close");

   xmlhttp.onreadystatechange= function() {
      if (xmlhttp.readyState==4) {
         document.getElementById(destDiv).innerHTML = xmlhttp.responseText;
      }
   }
   xmlhttp.send(params)
   return false;
}


function formData2QueryString(docForm) {

        var strSubmit       = '';
        var formElem;
        var strLastElemName = '';
        
        for (i = 0; i < docForm.elements.length; i++) {
                formElem = docForm.elements[i];
	strSubmit += formElem.name + 
                                '=' + escape(formElem.value) + '&'
	}
	return strSubmit;
}
</script>

<style type="text/css">
.tab-box { 
  border-bottom: 1px solid #DDD;
  padding-bottom:5px;
}
.tab-box a {
  border:1px solid #DDD;
  color:#666666;
  padding: 5px 15px;
  text-decoration:none;
  background-color: #eee;
}
.tab-box a.activeLink { 
  background-color: #FF8040; 
  border-bottom: 0; 
  padding: 6px 15px;
  font-family: value; arial; color: #000000 ; font-size: 1em; 
}
.tabcontent { border:1px solid #000000; border-top: 0;font-family: value; arial;  font-size: 1em ;}
.content {font-family: value; arial; color: #800080; }
.hide { display: none}
.footer {font-family: arial;color: #FF8040; font-size: 0.9em ;}
.small { border: 1px solid #EEE; padding: 5px; font-family: arial;color: #408080; }
</style>

<script type="text/javascript">
  $(document).ready(function() {
    $(".tabLink").each(function(){
      $(this).click(function(){
        tabeId = $(this).attr('id');
        $(".tabLink").removeClass("activeLink");
        $(this).addClass("activeLink");
        $(".tabcontent").addClass("hide");
        $("#"+tabeId+"-1").removeClass("hide")   
        return false;	  
      });
    });  
  });
</script>

</head>

<body>

 <div class="tab-box"> 
    <a href="javascript:;" class="tabLink activeLink" id="cont-1">DB TEST</a> 
  </div>
  
  
  <div class="tabcontent hide" id="cont-1-1"> 
     
<TABLE>    
<TR>
        <TD><B>DB Test</B></TD>
</TR>
<TR>
        <TD>
		<FORM NAME="cptest" ACTION="DBCPTest.jsp" METHOD="post">
		<HR><B>Connection Pool Testing Test </B><BR>
                JNDI NAME(given in resources.xml) <INPUT TYPE="text" NAME="Name" value="jdbc/newapp_dev"><BR>
		<INPUT TYPE="button" VALUE="Submit" onClick="post2page('DBCPTest.jsp',this.form,'DBresult')">
                </FORM>
        </TD>
</TR>


<TR><TD><HR></TD></TR>
		<TR><TD><DIV id="DBresult" class="content"> </DIV></TD></TR>
</TABLE>    
  </div> 
  

<div class="small"> 

<TABLE>    
<TR>
	<TD><div class="footer"> This JVM(<%= jvm%>) ruuning on<b> <%= hostName%> </b> and log for this instance is <b> <%= logDir%></b></dvi> </TD>
<TR>
</div>

</TABLE>    

</body>
</html>
