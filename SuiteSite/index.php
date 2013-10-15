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
            if(substr_compare($name, ".zip", -4, 4) === 0 && $name !== "" && $name !== ".")
                if($outputText === "")
                    $outputText = str_replace(".zip", "", $name, $count);
                else
                    $outputText = $outputText . "," . str_replace(".zip", "", $name, $count);
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
            if(substr_compare($name, ".zip", -4, 4) === 0 && $name !== "" && $name !== ".")
                $outputText = str_replace(".zip", "", $name, $count);
        }
        echo("$outputText");
    }
?>