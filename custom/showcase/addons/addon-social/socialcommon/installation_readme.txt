The following extension is a support extension for Social Connect AddOns.

--> By default this works with Storefront extensions called yacceleratorstorefront or yb2bacceleratorstorefront.

If you have renamed your Accelerator based Storefront(s) you will need to add the following entry to your project.properties or local.properties for each storefront.

<your_storefront_extension_name>.additionalWebSpringConfigs.socialcommon=classpath:/socialcommon/web/spring/socialcommon-b2c-web-spring.xml
<your_storefront_extension_name>.additionalWebSpringConfigs.socialcommon=classpath:/socialcommon/web/spring/socialcommon-b2b-web-spring.xml



--> You will need add an entry also to your localextension.xml 

	 <extension dir="${HYBRIS_BIN_DIR}/<pathtoextension>/socialcommon" />
OR
     Enable implicitly by including the new convenience 5.0 lazyload path feature  
     <path autoload="true" dir="${HYBRIS_BIN_DIR}/<commonpathtoaddons>" />
 
 
--> This extension requires the addoncommon extension. You should refer to the corresponding installation_readme.txt file in that extension to review install instructions.
 