package camping.dto;

public class Campsite {
	private int content_id;
	private String title;
	private String addr;
	private int sido_code;
	private int gugun_code;
	private double latitude;
	private double longitude;
	private String img;
	private String overview;
	
	public Campsite() {}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getSido_code() {
		return sido_code;
	}

	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}

	public int getGugun_code() {
		return gugun_code;
	}

	public void setGugun_code(int gugun_code) {
		this.gugun_code = gugun_code;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	@Override
	public String toString() {
		return "Campsite [content_id=" + content_id + ", title=" + title + ", addr=" + addr + ", sido_code=" + sido_code
				+ ", gugun_code=" + gugun_code + ", latitude=" + latitude + ", longitude=" + longitude + ", img=" + img
				+ ", overview=" + overview + "]";
	}
}
