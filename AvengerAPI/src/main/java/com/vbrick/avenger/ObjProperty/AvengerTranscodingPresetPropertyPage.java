package com.vbrick.avenger.ObjProperty;

public enum AvengerTranscodingPresetPropertyPage {
	transcodingpreset_presetlocator("//input[@name='presetName']"),
	transcodingpreset_descriptionlocator("//textarea[@ng-model='preset.description']"),
	transcodingpreset_outputtypelocator("//*[@name='container']"),
	transcodingpreset_preventupscalinglocator("//select[@name='profile']"), //preventUpscaling
	transcodingpreset_keyframeintervallocator("//input[@name='keyframeInterval']"),
	transcodingpreset_frameratelocator("//select[@name='framerate']"),
	transcodingpreset_widthlocator("//input[@placeholder='width']"),
	transcodingpreset_heightlocator("//input[@placeholder='height']"),
	transcodingpreset_audiobitratelocator("//select[@name='audioBitrate']"),
	transcodingpreset_audiosampleratelocator("//select[@name='audioSampleRate']"),
	transcodingpreset_createbuttonlocator("//*[contains(@class,'footer')]//button[2][contains(@type,'submit')]"),
	transcodingpreset_transcodingprofilelocator("videoProfile"),
	transcodingpreset_bitratelocator("//input[@name='bitrate']"),
	transcodingpreset_videobitratelocator("//select[@name='videoBitrateType']");
	
	private String property;
	private AvengerTranscodingPresetPropertyPage(String property) {
		this.setProperty(property);
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	

}
