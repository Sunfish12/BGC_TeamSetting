package model.MemberFavoredType;

//username	varchar(30)
//favoredType	varchar(30)
public class MemberFavoredTypeBean {
	private String username;
	private String favoredType;

	public String toString() {
		return username + "\n" + favoredType + "\n";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFavoredType() {
		return favoredType;
	}

	public void setFavoredType(String favoredType) {
		this.favoredType = favoredType;
	}

}
