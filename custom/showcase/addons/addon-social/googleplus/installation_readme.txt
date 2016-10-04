This extension is an Accelerator AddOn.

--> By default this works with Storefront extensions called yacceleratorstorefront or yb2bacceleratorstorefront.

If you have renamed your Accelerator based Storefront(s) you will need to add the following entry to your project.properties or local.properties for each storefront

<your_storefront_extension_name>.additionalWebSpringConfigs.googleplus=classpath:/googleplus/web/spring/googleplus-web-spring.xml

--> To enable this AddOn, you need to add the following entry to you Storefront extensioninfo.xml 

<requires-extension name="googleplus" />

--> You will need add an entry also to your localextension.xml 

	 <extension dir="${HYBRIS_BIN_DIR}/<pathtoaddon>/googleplus" />
OR
     Enable implicitly by including the new convenience 5.0 lazyload path feature  
     <path autoload="true" dir="${HYBRIS_BIN_DIR}/<commonpathtoaddons>" />

--> To complete the installation of this extension in the HAC : 
 - execute a update running system, essential data, localize types 
 - enable at least projectdata for yacceleratorcore/yb2bacceleratorcore and googleplus extension
 
 
--> This extension requires the socialcommon extension. You should refer to the corresponding installation_readme.txt file in that extension to review install instructions.
 