This extension is a support extension for other Facebook AddOns.

--> By default this works with Storefront extensions called yacceleratorstorefront or yb2bacceleratorstorefront.

If you have renamed your Accelerator based Storefront(s) you will need to add the following entry to your project.properties or local.properties for each storefront

<your_storefront_extension_name>.additionalWebSpringConfigs.facebookcommon=classpath:/facebookcommon/web/spring/facebookcommon-web-spring.xml

--> To Register the Application ID and Secret linked to your sites domain or a subdomain enter the following in your project.properties or local.properties

fbconnect.appid.<domain>=<app id>
fbconnect.secret.<domain>=<secret>

i.e.

fbconnect.appid.mydomain.com=123456789
fbconnect.secret.mydomain.com=kdjdkdk83939393djdjdjdjdj
fbconnect.appid.brand.mydomain.com=987654321
fbconnect.secret.brand.mydomain.com=3382djwxsxms282923dmsek

--> This extension requires the socialcommon extension. You should refer to the corresponding installation_readme.txt file in that extension to review install instructions.