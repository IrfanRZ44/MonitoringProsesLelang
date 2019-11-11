<?php

class Json_process extends CI_Controller {
	private $host = "localhost";
	private $user = "mor7com_dolby";
	private $password = "mor7com_dolby";
	private $namaDb = "mor7com_dolby";

	function loginUser(){
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
		$row_array['imei'] = "";

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
						$row_array['imei'] = $imei;
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

		$username = $data_user[0][username];
		$imei = $data_user[0][imei];
		$result = mysqli_query($kon, "UPDATE `users` SET `imei` = '$imei' WHERE `username` = '$username'");

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
			$row_array['imei'] = "";
			$row_array['response'] = "Failed to update IMEI";
			
			array_push($data_result,$row_array);
			echo json_encode($data_result);
		}
		
	}

	function getContract(){
		$content = trim(file_get_contents("php://input"));
		$decoded = json_decode($content);
		$username = $decoded->username;
		$level = $decoded->level;

		if (isset($username) && isset($level) && ($level != "")) {
			$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
			$data_user = array();
			if ($level == "pic" || $level == "admin" || $level == "officer") {
				$result = mysqli_query($kon, "SELECT * FROM `proyek`");

				if ($result) {
					$data_result = array();

					while ($row = mysqli_fetch_array($result)) {
						$row_array['id_proyek'] = $row['id_proyek'];
						$row_array['judulproyek'] = $row['judulproyek'];
						$row_array['nokontrak'] = $row['nokontrak'];
						$row_array['no_rak'] = $row['no_rak'];
						$row_array['lokasikerja'] = $row['lokasikerja'];
						$row_array['datetime'] = $row['datetime'];
						$row_array['step'] = $row['step'];
						$row_array['sub_step']  = $row['sub_step'];
						$row_array['komentar'] = $row['komentar'];
						$row_array['tgl_permintaanproyek'] = $row['tgl_permintaanproyek'];
						$row_array['tgl_pratender'] = $row['tgl_pratender'];
						$row_array['tgl_bidderlist'] = $row['tgl_bidderlist'];
						$row_array['tgl_rfq'] = $row['tgl_rfq'];
						$row_array['tgl_prakualifikasi'] = $row['tgl_prakualifikasi'];
						$row_array['tgl_aanwijizing'] = $row['tgl_aanwijizing'];
						$row_array['tgl_pembukaansampul1'] = $row['tgl_pembukaansampul1'];
						$row_array['tgl_pembukaansampul2'] = $row['tgl_pembukaansampul2'];
						$row_array['tgl_negosiasi'] = $row['tgl_negosiasi'];
						$row_array['tgl_penetapanpemenang'] = $row['tgl_penetapanpemenang'];
						$row_array['tgl_penunjukanpemenang'] = $row['tgl_penunjukanpemenang'];
						$row_array['response'] = "Success";

						array_push($data_result,$row_array);
					}
					
					echo json_encode($data_result);
				}else{
					$row_array['id_proyek'] = "";
					$row_array['judulproyek'] = "";
					$row_array['nokontrak'] = "";
					$row_array['no_rak'] = "";
					$row_array['lokasikerja'] = "";
					$row_array['datetime'] = "";
					$row_array['step'] = "";
					$row_array['sub_step']  = "";
					$row_array['komentar'] = "";
					$row_array['tgl_permintaanproyek'] = "";
					$row_array['tgl_pratender'] = "";
					$row_array['tgl_bidderlist'] = "";
					$row_array['tgl_rfq'] = "";
					$row_array['tgl_prakualifikasi'] = "";
					$row_array['tgl_aanwijizing'] = "";
					$row_array['tgl_pembukaansampul1'] = "";
					$row_array['tgl_pembukaansampul2'] = "";
					$row_array['tgl_negosiasi'] = "";
					$row_array['tgl_penetapanpemenang'] = "";
					$row_array['tgl_penunjukanpemenang'] = "";
					$row_array['response'] = "Failed getting result";

					array_push($data_user,$row_array);
					echo json_encode($data_user);
				}
			} else if ($level == "user") {
				$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
				$data_user = array();
				$result = mysqli_query($kon, "SELECT * FROM `users_proyek` WHERE `username` = '$username'");

				if ($result) {
					$data_result = array();

					while ($row = mysqli_fetch_array($result)) {

						$row_array['id_proyek'] = $row['id_proyek'];
						
						array_push($data_result,$row_array);
					}

					$this->getUserContract($data_result);
				}else{
					$row_array['id_proyek'] = "none";

					array_push($data_user,$row_array);
					echo json_encode($data_user);
				}
			} else{
				$row_array['id_proyek'] = "";
				$row_array['judulproyek'] = "";
				$row_array['nokontrak'] = "";
				$row_array['no_rak'] = "";
				$row_array['lokasikerja'] = "";
				$row_array['datetime'] = "";
				$row_array['step'] = "";
				$row_array['sub_step']  = "";
				$row_array['komentar'] = "";
				$row_array['tgl_permintaanproyek'] = "";
				$row_array['tgl_pratender'] = "";
				$row_array['tgl_bidderlist'] = "";
				$row_array['tgl_rfq'] = "";
				$row_array['tgl_prakualifikasi'] = "";
				$row_array['tgl_aanwijizing'] = "";
				$row_array['tgl_pembukaansampul1'] = "";
				$row_array['tgl_pembukaansampul2'] = "";
				$row_array['tgl_negosiasi'] = "";
				$row_array['tgl_penetapanpemenang'] = "";
				$row_array['tgl_penunjukanpemenang'] = "";
				$row_array['response'] = "User Level doesn't Available";

				array_push($data_user,$row_array);
				echo json_encode($data_user);
			}
		} else {
			$row_array['id_proyek'] = "";
			$row_array['judulproyek'] = "";
			$row_array['nokontrak'] = "";
			$row_array['no_rak'] = "";
			$row_array['lokasikerja'] = "";
			$row_array['datetime'] = "";
			$row_array['step'] = "";
			$row_array['sub_step']  = "";
			$row_array['komentar'] = "";
			$row_array['tgl_permintaanproyek'] = "";
			$row_array['tgl_pratender'] = "";
			$row_array['tgl_bidderlist'] = "";
			$row_array['tgl_rfq'] = "";
			$row_array['tgl_prakualifikasi'] = "";
			$row_array['tgl_aanwijizing'] = "";
			$row_array['tgl_pembukaansampul1'] = "";
			$row_array['tgl_pembukaansampul2'] = "";
			$row_array['tgl_negosiasi'] = "";
			$row_array['tgl_penetapanpemenang'] = "";
			$row_array['tgl_penunjukanpemenang'] = "";
			$row_array['response'] = "Failed to send data";

			array_push($data_user,$row_array);
			echo json_encode($data_user);
		}
		mysqli_close($con);
	}
	
	function getUserContract($data_result){
		$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
		$result = mysqli_query($kon, "SELECT * FROM `proyek`");
		$data_json = array();

		if ($result) {

			while ($row = mysqli_fetch_array($result)) {
				for ($i=0; $i < sizeof($data_result); $i++) { 
					if ($row['id_proyek'] == $data_result[$i]['id_proyek']) {
						$row_array['id_proyek'] = $row['id_proyek'];
						$row_array['judulproyek'] = $row['judulproyek'];
						$row_array['nokontrak'] = $row['nokontrak'];
						$row_array['no_rak'] = $row['no_rak'];
						$row_array['lokasikerja'] = $row['lokasikerja'];
						$row_array['datetime'] = $row['datetime'];
						$row_array['step'] = $row['step'];
						$row_array['sub_step']  = $row['sub_step'];
						$row_array['komentar'] = $row['komentar'];
						$row_array['tgl_permintaanproyek'] = $row['tgl_permintaanproyek'];
						$row_array['tgl_pratender'] = $row['tgl_pratender'];
						$row_array['tgl_bidderlist'] = $row['tgl_bidderlist'];
						$row_array['tgl_rfq'] = $row['tgl_rfq'];
						$row_array['tgl_prakualifikasi'] = $row['tgl_prakualifikasi'];
						$row_array['tgl_aanwijizing'] = $row['tgl_aanwijizing'];
						$row_array['tgl_pembukaansampul1'] = $row['tgl_pembukaansampul1'];
						$row_array['tgl_pembukaansampul2'] = $row['tgl_pembukaansampul2'];
						$row_array['tgl_negosiasi'] = $row['tgl_negosiasi'];
						$row_array['tgl_penetapanpemenang'] = $row['tgl_penetapanpemenang'];
						$row_array['tgl_penunjukanpemenang'] = $row['tgl_penunjukanpemenang'];
						$row_array['response'] = "Success";

						array_push($data_json,$row_array);
					}
				}

			}

			echo json_encode($data_json);
		}else{
			$row_array['id_proyek'] = "";
			$row_array['judulproyek'] = "";
			$row_array['nokontrak'] = "";
			$row_array['no_rak'] = "";
			$row_array['lokasikerja'] = "";
			$row_array['datetime'] = "";
			$row_array['step'] = "";
			$row_array['sub_step']  = "";
			$row_array['komentar'] = "";
			$row_array['tgl_permintaanproyek'] = "";
			$row_array['tgl_pratender'] = "";
			$row_array['tgl_bidderlist'] = "";
			$row_array['tgl_rfq'] = "";
			$row_array['tgl_prakualifikasi'] = "";
			$row_array['tgl_aanwijizing'] = "";
			$row_array['tgl_pembukaansampul1'] = "";
			$row_array['tgl_pembukaansampul2'] = "";
			$row_array['tgl_negosiasi'] = "";
			$row_array['tgl_penetapanpemenang'] = "";
			$row_array['tgl_penunjukanpemenang'] = "";
			$row_array['response'] = "Failed getting result to server";

			array_push($data_json,$row_array);
			echo json_encode($data_json);
		}
	}
	
	function getResultScanContract(){
		$content = trim(file_get_contents("php://input"));
		$decoded = json_decode($content);
		$username = $decoded->username;
		$level = $decoded->level;
		$idContract = $decoded->idContract;

		if (isset($username) && isset($level) && ($level != "") && isset($idContract)) {
			$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
			$data_user = array();
			if ($level == "admin" || $level == "pic" || $level == "officer") {
				$result = mysqli_query($kon, "SELECT * FROM `proyek` WHERE `id_proyek` = '$result'");

				if ($result) {
					$data_result = array();

					while ($row = mysqli_fetch_array($result)) {
						$row_array['id_proyek'] = $row['id_proyek'];
						$row_array['judulproyek'] = $row['judulproyek'];
						$row_array['nokontrak'] = $row['nokontrak'];
						$row_array['no_rak'] = $row['no_rak'];
						$row_array['lokasikerja'] = $row['lokasikerja'];
						$row_array['datetime'] = $row['datetime'];
						$row_array['step'] = $row['step'];
						$row_array['sub_step']  = $row['sub_step'];
						$row_array['komentar'] = $row['komentar'];
						$row_array['tgl_permintaanproyek'] = $row['tgl_permintaanproyek'];
						$row_array['tgl_pratender'] = $row['tgl_pratender'];
						$row_array['tgl_bidderlist'] = $row['tgl_bidderlist'];
						$row_array['tgl_rfq'] = $row['tgl_rfq'];
						$row_array['tgl_prakualifikasi'] = $row['tgl_prakualifikasi'];
						$row_array['tgl_aanwijizing'] = $row['tgl_aanwijizing'];
						$row_array['tgl_pembukaansampul1'] = $row['tgl_pembukaansampul1'];
						$row_array['tgl_pembukaansampul2'] = $row['tgl_pembukaansampul2'];
						$row_array['tgl_negosiasi'] = $row['tgl_negosiasi'];
						$row_array['tgl_penetapanpemenang'] = $row['tgl_penetapanpemenang'];
						$row_array['tgl_penunjukanpemenang'] = $row['tgl_penunjukanpemenang'];
						$row_array['response'] = "Success";

						array_push($data_result,$row_array);
					}
					
					echo json_encode($data_result);
				}else{
					echo json_encode("Failed result");
				}
			}else if ($level == "user") {
				$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
				$data_user = array();
				$result = mysqli_query($kon, "SELECT * FROM `users_proyek` WHERE `username` = '$username' AND `id_proyek` = '$idContract'");

				if ($result) {
					$contractNotAvailable = true;

					while ($row = mysqli_fetch_array($result)) {
						$contractNotAvailable = false;
						$this->getUserResultContract($idContract);
					}
					
					if($contractNotAvailable){
						$row_array['id_proyek'] = "";
						$row_array['judulproyek'] = "";
						$row_array['nokontrak'] = "";
						$row_array['no_rak'] = "";
						$row_array['lokasikerja'] = "";
						$row_array['datetime'] = "";
						$row_array['step'] = "";
						$row_array['sub_step']  = "";
						$row_array['komentar'] = "";
						$row_array['tgl_permintaanproyek'] = "";
						$row_array['tgl_pratender'] = "";
						$row_array['tgl_bidderlist'] = "";
						$row_array['tgl_rfq'] = "";
						$row_array['tgl_prakualifikasi'] = "";
						$row_array['tgl_aanwijizing'] = "";
						$row_array['tgl_pembukaansampul1'] = "";
						$row_array['tgl_pembukaansampul2'] = "";
						$row_array['tgl_negosiasi'] = "";
						$row_array['tgl_penetapanpemenang'] = "";
						$row_array['tgl_penunjukanpemenang'] = "";
						$row_array['response'] = "Anda tidak mempunyai akses ke contract ini";

						array_push($data_user,$row_array);
						echo json_encode($data_user);
					}
				}else{
					$row_array['id_proyek'] = "none";

					array_push($data_user,$row_array);
					echo json_encode($data_user);
				}
			}else{
				echo json_encode("User Level doesn't Available");
			}
		} else {
			$row_array['id_proyek'] = "";
			$row_array['judulproyek'] = "";
			$row_array['nokontrak'] = "";
			$row_array['no_rak'] = "";
			$row_array['lokasikerja'] = "";
			$row_array['datetime'] = "";
			$row_array['step'] = "";
			$row_array['sub_step']  = "";
			$row_array['komentar'] = "";
			$row_array['tgl_permintaanproyek'] = "";
			$row_array['tgl_pratender'] = "";
			$row_array['tgl_bidderlist'] = "";
			$row_array['tgl_rfq'] = "";
			$row_array['tgl_prakualifikasi'] = "";
			$row_array['tgl_aanwijizing'] = "";
			$row_array['tgl_pembukaansampul1'] = "";
			$row_array['tgl_pembukaansampul2'] = "";
			$row_array['tgl_negosiasi'] = "";
			$row_array['tgl_penetapanpemenang'] = "";
			$row_array['tgl_penunjukanpemenang'] = "";
			$row_array['response'] = "Error data yang dikirim kosong";

			array_push($data_json,$row_array);
			echo json_encode($data_json);
		}
		mysqli_close($con);
	}
	
	function getUserResultContract($data_result){
		$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
		$result = mysqli_query($kon, "SELECT * FROM `proyek` WHERE `id_proyek` = '$data_result'");
		$data_json = array();

		if ($result) {

			while ($row = mysqli_fetch_array($result)) {
				$row_array['id_proyek'] = $row['id_proyek'];
				$row_array['judulproyek'] = $row['judulproyek'];
				$row_array['nokontrak'] = $row['nokontrak'];
				$row_array['no_rak'] = $row['no_rak'];
				$row_array['lokasikerja'] = $row['lokasikerja'];
				$row_array['datetime'] = $row['datetime'];
				$row_array['step'] = $row['step'];
				$row_array['sub_step']  = $row['sub_step'];
				$row_array['komentar'] = $row['komentar'];
				$row_array['tgl_permintaanproyek'] = $row['tgl_permintaanproyek'];
				$row_array['tgl_pratender'] = $row['tgl_pratender'];
				$row_array['tgl_bidderlist'] = $row['tgl_bidderlist'];
				$row_array['tgl_rfq'] = $row['tgl_rfq'];
				$row_array['tgl_prakualifikasi'] = $row['tgl_prakualifikasi'];
				$row_array['tgl_aanwijizing'] = $row['tgl_aanwijizing'];
				$row_array['tgl_pembukaansampul1'] = $row['tgl_pembukaansampul1'];
				$row_array['tgl_pembukaansampul2'] = $row['tgl_pembukaansampul2'];
				$row_array['tgl_negosiasi'] = $row['tgl_negosiasi'];
				$row_array['tgl_penetapanpemenang'] = $row['tgl_penetapanpemenang'];
				$row_array['tgl_penunjukanpemenang'] = $row['tgl_penunjukanpemenang'];
				$row_array['response'] = "Success";

				array_push($data_json,$row_array);
			}


			echo json_encode($data_json);
		}else{
			$row_array['id_proyek'] = "";
			$row_array['judulproyek'] = "";
			$row_array['nokontrak'] = "";
			$row_array['no_rak'] = "";
			$row_array['lokasikerja'] = "";
			$row_array['datetime'] = "";
			$row_array['step'] = "";
			$row_array['sub_step']  = "";
			$row_array['komentar'] = "";
			$row_array['tgl_permintaanproyek'] = "";
			$row_array['tgl_pratender'] = "";
			$row_array['tgl_bidderlist'] = "";
			$row_array['tgl_rfq'] = "";
			$row_array['tgl_prakualifikasi'] = "";
			$row_array['tgl_aanwijizing'] = "";
			$row_array['tgl_pembukaansampul1'] = "";
			$row_array['tgl_pembukaansampul2'] = "";
			$row_array['tgl_negosiasi'] = "";
			$row_array['tgl_penetapanpemenang'] = "";
			$row_array['tgl_penunjukanpemenang'] = "";
			$row_array['response'] = "Failed getting result to server";

			array_push($data_json,$row_array);
			echo json_encode($data_json);
		}
	}
	
	function cekImei(){
		$content = trim(file_get_contents("php://input"));
		$decoded = json_decode($content);
		$username = $decoded->username;
		$imei = $decoded->imei;

		if (isset($username) && isset($imei) && ($imei != "")) {
			$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
			
			
			$result = mysqli_query($kon, "SELECT `imei` FROM `users` WHERE `username` = '$username'");
			

			if ($result) {
				$data_result = array();

				while ($row = mysqli_fetch_array($result)) {
					if($row['imei'] == $imei){
						echo json_encode(array("response" => "Match"));
					}
					else{
						echo json_encode(array("response" => "Unmathced"));
					}
				}
			} else {
				echo json_encode(array("response" => "Failed to send data"));
			}
			mysqli_close($con);
		}
	}
	
	function getStepContract(){
		$content = trim(file_get_contents("php://input"));
		$decoded = json_decode($content);
		$id_proyek = $decoded->id_proyek;
		$userRole = $decoded->userRole;
		$userName = $decoded->userName;

		if ($userRole == "pic" || $userRole == "admin" || $userRole == "officer") {
			$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
			
			$result = mysqli_query($kon, "SELECT * FROM `files` WHERE `id_proyek` = '$id_proyek'");
			

			if ($result) {
				$data_result = array();
				
				while ($row = mysqli_fetch_array($result)) {
					$row_array['id_files'] = $row['id_files'];
					$row_array['nama_file'] = $row['nama_file'];
					$row_array['url'] = $row['url'];
					$row_array['id_proyek'] = $row['id_proyek'];
					$row_array['id_dokumen'] = $row['id_dokumen'];
					$row_array['datetime_create'] = $row['datetime_create'];
					$row_array['deskripsi_file'] = $row['deskripsi_file'];
					$row_array['response'] = "Success";

					array_push($data_result,$row_array);
				}
				
				echo json_encode($data_result);
			} else {
				$row_array['id_files'] = "";
				$row_array['nama_file'] = "";
				$row_array['url'] = "";
				$row_array['id_proyek'] = "";
				$row_array['id_dokumen'] = "";
				$row_array['datetime_create'] = "";
				$row_array['deskripsi_file'] = "";
				$row_array['response'] = "Failed to send data";
				
				array_push($data_result,$row_array);
				echo json_encode($data_result);
			}
			mysqli_close($con);
		} else if ($userRole == "user") {
			$kon = mysqli_connect($this->host, $this->user, $this->password, $this->namaDb);
			$result = mysqli_query($kon, "SELECT * FROM `files` WHERE `id_proyek` = '$id_proyek'");
			
			$data_result = array();


			if ($result) {
				while ($row = mysqli_fetch_array($result)) {

					$row_array['id_files'] = $row['id_files'];
					$row_array['nama_file'] = $row['nama_file'];
					$row_array['url'] = $row['url'];
					$row_array['id_proyek'] = $row['id_proyek'];
					$row_array['id_dokumen'] = $row['id_dokumen'];
					$row_array['datetime_create'] = $row['datetime_create'];
					$row_array['deskripsi_file'] = $row['deskripsi_file'];
					$row_array['response'] = "Success";

					array_push($data_result,$row_array);
					
				}
				
				if($data_result[0]['response'] != "Success"){
					$row_array['id_files'] = "";
					$row_array['nama_file'] = "";
					$row_array['url'] = "";
					$row_array['id_proyek'] = "";
					$row_array['id_dokumen'] = "";
					$row_array['datetime_create'] = "";
					$row_array['deskripsi_file'] = "";
					$row_array['response'] = "No Docs Available";

					array_push($data_result,$row_array);
					echo json_encode($data_result);
				}
				else{
					echo json_encode($data_result);
				}
				

			} else {
				$row_array['id_files'] = "";
				$row_array['nama_file'] = "";
				$row_array['url'] = "";
				$row_array['id_proyek'] = "";
				$row_array['id_dokumen'] = "";
				$row_array['datetime_create'] = "";
				$row_array['deskripsi_file'] = "";
				$row_array['response'] = "Failed to send data";
				
				array_push($data_result,$row_array);
				echo json_encode($data_result);
				
				
			}
			mysqli_close($con);
		}
	}
}
?>