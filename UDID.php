<!DOCTYPE html>
<html>
<head>
	<title>UDID Device Info</title>
</head>
<body>

	<h1>UDID Device Info</h1>

	<form method="post" action="submit-udid.php">
		<label for="udid">UDID:</label>
		<input type="text" id="udid" name="udid" placeholder="Enter UDID...">
		<br><br>
		<label for="device_name">Device Name:</label>
		<input type="text" id="device_name" name="device_name" placeholder="Enter device name...">
		<br><br>
		<label for="device_model">Device Model:</label>
		<input type="text" id="device_model" name="device_model" placeholder="Enter device model...">
		<br><br>
		<input type="submit" value="Submit">
	</form>

</body>
</html>
