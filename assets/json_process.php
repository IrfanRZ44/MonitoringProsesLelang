<?php

class Json_process extends CI_Controller {
	private $host = "localhost";
	private $user = "mor7com_dolby";
	private $password = "mor7com_dolby";
	private $namaDb = "mor7com_dolby";

	function testes(){
		$content = trim(file_get_contents("php://input"));
		$decoded = json_decode($content);
		$username = $decoded->username;
		$password = $decoded->password;
		$imei = $decoded->imei;

		$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
		$data_user = array();
		$userServer = "";
		$passServer = "";
		$levelServer = "";
		$sessionServer = "";
		$hpServer = "";
		$emailServer = "";
		$namaServer = "";
		$row_array['username'] = $userServer;
		$row_array['password'] = $passServer;
		$row_array['level'] = $levelServer;
		$row_array['session_id'] = $sessionServer;
		$row_array['nohp'] = $hpServer;
		$row_array['email'] = $emailServer;
		$row_array['nama'] = $namaServer;

		if (isset($username) && isset($password) && isset($imei) && ($imei != "")) {
			$result = mysqli_query($kon, "SELECT * FROM `users`");
			if ($result) {
				$userExist = false;

				while ($row = mysqli_fetch_array($result)) {
					if($row['username'] == $username){
						$userExist = true;
						$userServer = $row['username'];
						$passServer = $row['password'];
						$levelServer = $row['level'];
						$sessionServer = $row['session_id'];
						$hpServer = $row['nohp'];
						$emailServer = $row['email'];
						$namaServer = $row['nama'];
					}
				}
				if ($userExist){
					if($passServer == md5($password)){
						$row_array['username'] = $userServer;
						$row_array['password'] = $passServer;
						$row_array['level'] = $levelServer;
						$row_array['session_id'] = $sessionServer;
						$row_array['nohp'] = $hpServer;
						$row_array['email'] = $emailServer;
						$row_array['nama'] = $namaServer;
						$row_array['response'] = "Success";

						array_push($data_user,$row_array);
						$this->setImei($data_user, $imei);
					}
					else{

						$row_array['response'] = "Wrong password";
						array_push($data_user,$row_array);
						echo json_encode($data_user);
					}
				}
				else{
					$row_array['response'] = "Username doesn't exist";
					array_push($data_user,$row_array);
					echo json_encode($data_user);
				}
			}
			else{
				$row_array['response'] = "Fail connect to server";
				array_push($data_user,$row_array);
				echo json_encode($data_user);
			}
		} else {
			$row_array['response'] = "Failed to send data";
			array_push($data_user,$row_array);
			echo json_encode($data_user);
		}
		mysqli_close($con);
	}

	function setImei($data_user, $imei){
		$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);

		$result = mysqli_query($kon, "UPDATE `users` SET `imei` = ".$imei." WHERE `users`.`username` = ".$data_user['username']);

		if ($result) {
			echo json_encode($data_user);
		}
		else{
			$data_result = array();
			$row_array['username'] = "";
			$row_array['password'] = "";
			$row_array['level'] = "";
			$row_array['session_id'] = "";
			$row_array['nohp'] = "";
			$row_array['email'] = "";
			$row_array['nama'] = "";
			$row_array['response'] = "Failed to update IMEI".mysqli_error($result);

			array_push($data_result,$row_array);
			echo json_encode($data_result);
		}
		
	}
}
?>