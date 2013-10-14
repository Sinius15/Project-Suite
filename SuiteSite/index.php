<!DOCTYPE html>

<head>
    <script type="text/javascript" src="jquery-1.10.2.min.js"></script>
    <script>

    </script>
    <script>

        if (typeof String.prototype.startsWith != 'function') {
            String.prototype.startsWith = function (str){
                return this.indexOf(str) == 0;
            };
        }

        if (typeof String.prototype.endsWith != 'function') {
            String.prototype.endsWith = function (str){
                return this.slice(-str.length) == str;
            };
        }

        var suite = {};

        suite.latestVersion = "a0.0"


        var param = window.location.search.replace("?", "");
        //if (param === "latestVersion")
        //    document.write(suite.latestVersion);
        //if (param.endsWith(".zip"))
        //    document.write('<a href="' + param + '"></a>');
        //else
        //    document.write("invalid requist");
        if (param === "")
            document.write('<p>This is the page of "Project Suite".</p> <p>In this project davidot and sinius15 have bundeled their capabilitys to make an awsome game.</p>');
        
    </script>
</head>

<body>
    <?php
        if($_SERVER['QUERY_STRING'] == 'versionList'){
             // Opens directory
            $myDirectory=opendir("./versions");
            while($entryName=readdir($myDirectory)) {
                $dirArray[]=($entryName);
            }
            $indexCount=count($dirArray);
            closedir($myDirectory);
            $outputText = "";
            for($index=0; $index < $indexCount; $index++) {
                $name=$dirArray[$index];
                if(substr_compare($name, ".jar", -4, 4) === 0 && $name !== "" && $name !== ".")
                    if($outputText === "")
                        $outputText = $name;
                    else
                        $outputText = $outputText . "," . $name;
            }
            echo("$outputText");
        }
        if($_SERVER['QUERY_STRING'] == 'latestVersion'){
             // Opens directory
            $myDirectory=opendir("./versions");
            while($entryName=readdir($myDirectory)) {
                $dirArray[]=($entryName);
            }
            $indexCount=count($dirArray);
            closedir($myDirectory);
            $outputText = "";
            for($index=0; $index < $indexCount; $index++) {
                $name=$dirArray[$index];
                if(substr_compare($name, ".jar", -4, 4) === 0 && $name !== "" && $name !== ".")
                    $outputText = str_replace(".jar", "", $name, $count);
            }
            echo("$outputText");
        }
    ?>
</body>
</html>