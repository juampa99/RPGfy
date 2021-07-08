# RPGfy Plugin for Spigot

RPGfy is a spigot plugin that adds a compendium of RPG elements to minecraft.

Features: 
* Customizable healthbars (see config.yml)
* Custom item generator (/spawngear command)
* Drop tables (see Droptable section)

Upcoming features:
* Save custom items and add them to the drop tables
* Better effects for weapons 
* Special abilities for weapons/armor
* More types of weapons
* Combo effects


## Droptable 
<small> This feature is still developing and is not
completly functional yet, nonetheless, 
here's a small guide to help you get accustommed to it</small>

Edit the droptable.yml file located in the plugins/RPGfy folder to
add custom droptables to entities.

Each entry must look like this: 

\<mobname>:<br>&ensp;
    drop:<br>&ensp;&ensp;
        item1:<br> &ensp;&ensp;&ensp;&ensp;
            name: \<itemname1><br>&ensp;&ensp;&ensp;&ensp;
            dropchance: \<dropchancePercentage1><br>&ensp;&ensp;
        item2:<br>&ensp;&ensp;&ensp;&ensp;
            name: \<itemname2><br>&ensp;&ensp;&ensp;&ensp;
            dropchance: \<dropchancePercentage2>

See droptable.yml for a real example

Supported item traits

<table style="color: black;border-radius: 15px; background-color: bisque">
    <tr>
        <th>Trait</th>
        <th>Description</th>
        <th>Bounds</th>
    </tr>
    <tr>
        <td>name</td>
        <td>Item's name, must match the name of a custom item
        or a <a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html">MATERIAL</a>, example: DIAMOND_SWORD, myCustomArmor</td>
        <td style="text-align: center;">-</td>
    </tr>
    <tr>
        <td>dropchance</td>
        <td>Drop chance of the item specified as percentage, example: 0.01, 1, 10</td>
        <td>1.0E-16 - 100</td>
    </tr>
    <tr>
        <td>minQuantity</td>
        <td>Minimum amount of item that will drop if it does drop</td>
        <td>0 - 64</td>
    </tr>
    <tr>
        <td>maxQuantity</td>
        <td>Max amount of item that will drop if it does drop</td>
        <td>1 - 64</td>
    </tr>
</table>

