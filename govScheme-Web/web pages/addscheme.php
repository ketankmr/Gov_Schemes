<link href="css/style_addscheme.css" rel="stylesheet">

<?php
   session_start();

  if(!isset($_SESSION['user']))
  {

       echo '<script type="text/javascript">';
       echo 'alert("Access Denied. Login to view this Page.");';
       echo 'window.location.href = "../web pages/login.php";';
       echo '</script>';
     }

?>

<script type="text/javascript" src="jquery-1.2.1.pack.js"></script>
<script type="text/javascript">
	function lookup(inputString) {
		if(inputString.length == 0) {
			// Hide the suggestion box.
			$('#suggestions').hide();
		} else {
			$.post("rpc.php", {queryString: ""+inputString+""}, function(data){
				if(data.length >0) {
					$('#suggestions').show();
					$('#autoSuggestionsList').html(data);
				}
			});
		}
	} // lookup

	function fill(thisValue) {
		$('#Location').val(thisValue);
		setTimeout("$('#suggestions').hide();", 200);
	}
</script>


<div class="container">
  <form id="contact" action="http://hosting619.96.lt/api/AddSchemes" method="post">
    <h3>Scheme Details</h3>

    <fieldset>
      <input name="SCHEME" placeholder="Scheme Name" type="text" tabindex="1" required autofocus>
    </fieldset>

<fieldset>
		<select name="CATEGORY" style="width: inherit;">
	     <option value="NULL">Choose Category</option>
	     <option value="Agriculture">Agriculture</option>
	     <option value="Education">Education</option>
	     <option value="Child Development">Child Development</option>
			 <option value="Mother Care">Mother Care</option>
			 <option value="Rural Development">Rural Development</option>
    </select>

	</fieldset>

<fieldset>
	<input type="url" name="URL" placeholder="Enter relevant img url">
<fieldset>
    <input type="text" size="30" name="LOCATION" value="" id="Location" placeholder="Location" onkeyup="lookup(this.value);" onblur="fill();" />

        <div class="suggestionsBox" id="suggestions" style="display: none;">
         <img src="assets/upArrow.png" style="position: relative; top: -12px; left: 30px;" alt="upArrow" />
           <div class="suggestionList" id="autoSuggestionsList">
           &nbsp;
           </div>
        </div>
</fieldset>

     <fieldset>
      <textarea name="DESCRIPTION" placeholder="Description" tabindex="4" required></textarea>
    </fieldset>

    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Add Scheme</button>
    </fieldset>

  </form>
</div>
