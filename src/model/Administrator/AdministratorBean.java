package model.Administrator;

public class AdministratorBean {

	private String adminUsername;
	private byte[] adminPswd;
	private String imgFileName;
	private byte[] adminMemberImage;

	public String toString() {
		return adminUsername + "\n" + adminPswd + "\n" + imgFileName + "\n"
				+ adminMemberImage;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public byte[] getAdminPswd() {
		return adminPswd;
	}

	public void setAdminPswd(byte[] adminPswd) {
		this.adminPswd = adminPswd;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public byte[] getAdminMemberImage() {
		return adminMemberImage;
	}

	public void setAdminMemberImage(byte[] adminMemberImage) {
		this.adminMemberImage = adminMemberImage;
	}

}
