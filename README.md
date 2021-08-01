# RPGfy Plugin for Spigot

RPGfy is a spigot plugin that adds a compendium of RPG elements to minecraft.

### Table of contents
- [Droptable](#droptable)
- [Custom Items](#custom-items)
- [Healthbar](#healthbar)
- [Developers](#developers)

Features: 
* Customizable healthbars (see config.yml)
* Custom item generator (/spawngear command)
* Drop tables (see Droptable section)
* Save custom items and add them to the drop tables (see Custom Items section)

Upcoming features (in order of priority):
* Better effects for weapons
* More types of weapons
* Special abilities for weapons/armor
* Questing
* Combo effects


## Droptable

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

<table style="border-radius: 15px; background-color: #000000">
    <tr>
        <th>Trait</th>
        <th>Description</th>
        <th>Bounds</th>
        <th>Default</th>
        <th>Required</th>
    </tr>
    <tr>
        <td>name</td>
        <td>Name of the item</td>
        <td style="text-align: center;">-</td>
        <td>Defaults to the type of the item</td>
        <td style="text-align: center;">No</td>
    </tr>
    <tr>
        <td>type</td>
        <td>Must match the name of a custom item
        or a <a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html">MATERIAL</a>, example: DIAMOND_SWORD, MyCustomArmor</td>
        <td style="text-align: center;">-</td>
        <td style="text-align: center;">Nothing, the item won't be created</td>    
        <td style="text-align: center;">Yes</td>
    </tr>
    <tr>
        <td>dropchance</td>
        <td>Drop chance of the item specified as percentage, example: 0.01, 1, 10</td>
        <td>1.0E-16 - 100</td>
        <td style="text-align: center;">Nothing, item won't be created</td>    
        <td style="text-align: center;">Yes</td>
    </tr>
    <tr>
        <td>minQuantity</td>
        <td>Minimum amount of item that will drop if it does drop</td>
        <td>0 - 64</td>
        <td style="text-align: center;">0</td>    
        <td style="text-align: center;">No</td>
    </tr>
    <tr>
        <td>maxQuantity</td>
        <td>Max amount of item that will drop if it does drop</td>
        <td>1 - 64</td>
        <td style="text-align: center;">1</td>    
        <td style="text-align: center;">No</td>
    </tr>
</table>

## Custom Items

Custom items are loaded through a config YAML file in the plugin's configuration folder,
they can be spawned using the /spawngear command or added to a mob's droptable.

#### General item traits 

<table style="border-radius: 15px; background-color: #000000">
    <tr>
        <th>Trait</th>
        <th>Description</th>
        <th>Accepts</th>
        <th>Default</th>
        <th>Required</th>
    </tr>
    <tr>
        <td>name</td>
        <td>Name of the item</td>
        <td style="text-align: center;">Text</td>
        <td>Defaults to the type of the item</td>
        <td style="text-align: center;">No</td>
    </tr>
    <tr>
        <td>type</td>
        <td>Must match the name of a custom item
        or a <a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html">MATERIAL</a>, example: DIAMOND_SWORD, MyCustomArmor</td>
        <td style="text-align: center;">Material or custom item name</td>
        <td style="text-align: center;">Nothing, the item won't be created</td>    
        <td style="text-align: center;">Yes</td>
    </tr>
    <tr>
        <td>lore</td>
        <td>Item description</td>
        <td style="text-align: center;">Text</td>
        <td style="text-align: center;">Empty string</td>    
        <td style="text-align: center;">No</td>
    <tr>
        <td>effects</td>
        <td>Item effects, must match the type of item (an armor effect if items is armor, weapon effect if weapon is a weapon)
        must be written as a list (see above for an example or in the custom_items.yml)
        </td>
        <td style="text-align: center;">List of effects</td>
        <td style="text-align: center;">No effects</td>    
        <td style="text-align: center;">No</td>
    </tr>
</table>

#### Weapon-specific traits

<table style="border-radius: 15px; background-color: #000000">
    <tr>
        <th>Trait</th>
        <th>Description</th>
        <th>Accepts</th>
        <th>Default</th>
        <th>Required</th>
    </tr>
    <tr>
        <td>damage</td>
        <td>Weapon damage</td>
        <td style="text-align: center;">0 - inf</td>
        <td>Defaults to the damage of the type of the item</td>
        <td style="text-align: center;">No</td>
    </tr>
    <tr>
        <td>attackSpeed</td>
        <td>Delay between attacks in seconds</td>
        <td style="text-align: center;">0 - inf</td>
        <td>Defaults to the attack speed of the type of the item</td>
        <td style="text-align: center;">No</td>
    </tr>
    <tr>
        <td>skin</td>
        <td>Weapon skin</td>
        <td style="text-align: center;">Red/White/Grey/Brown/Cyan</td>
        <td>Defaults to the weapon's base skin (usually cyan)</td>
        <td style="text-align: center;">No</td>
    </tr>
    
</table>

### Armor-specific traits

Documentation in progress

## Healthbar

Documentation in progress

## Developers

Documentation in progress