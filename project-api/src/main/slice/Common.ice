#pragma
[["java:package:com.project.api.slice"]]
module common {
	class SimpleResponse {
	   	bool success = true;
	   	string message;
	};

	class BussinessResponse extends SimpleResponse{
	    string jsonObject;
	};
};