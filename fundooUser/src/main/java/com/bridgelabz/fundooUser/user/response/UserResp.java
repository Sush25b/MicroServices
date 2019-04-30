package com.bridgelabz.fundooNotes.user.response;

public class UserResp
{
		private String message;
		private long timeStamp;
		private int status;
		private String token;
		
		public UserResp()
		{}

		public UserResp(String message, long timeStamp, int status, String token)
		{
			this.message = message;
			this.timeStamp = timeStamp;
			this.status = status;
			this.token = token;
		}

		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public long getTimeStamp() {
			return timeStamp;
		}
		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		
		@Override
		public String toString() {
			return "UserResp [message=" + message + ", timeStamp=" + timeStamp + ", status=" + status + ", token="
					+ token + "]";
		}

}
