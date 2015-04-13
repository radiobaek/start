package net.opensg.tcs.commons.libs.core;

import java.util.List;
import java.util.Map;

public class GeneralParam {
	public GeneralParamType ParamType = GeneralParamType.String;
	public String ParamName = "";
	public Object ParamValue = null;
	
	public String getParamValue_String() {
		if (!this.ParamType.equals(GeneralParamType.String)) return null;
		return Convert.ToString(ParamValue);
	}
	
	public List<String> getParamValue_ListString() {
		if (!this.ParamType.equals(GeneralParamType.ListString)) return null;
		return (List<String>)ParamValue;
	}

	public Map<String,String> getParamValue_MapString() {
		if (!this.ParamType.equals(GeneralParamType.MapString)) return null;
		return (Map<String,String>)ParamValue;
	}

}
