package com.existencesmp.museum

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.event.listen
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.gui.openGUI
import net.axay.kspigot.items.addLore
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.axay.kspigot.main.KSpigot
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.util.Vector

class Museum : KSpigot() {
    companion object {
        lateinit var INSTANCE: Museum
    }

    override fun load() {
        INSTANCE = this
    }

    override fun startup() {
        val navigator = itemStack(Material.RECOVERY_COMPASS) {
            meta {
                name = literalText("Museum Navigator") {
                    color = KColors.AQUA
                    italic = false
                }
                addLore {
                    +literalText("→ ʀɪɢʜᴛ-ᴄʟɪᴄᴋ ᴛᴏ ᴏᴘᴇɴ") {
                        color = KColors.LIGHTGRAY
                        italic = false
                    }
                }
            }
        }

        val navigatorGui = kSpigotGUI(GUIType.FOUR_BY_NINE) {
            title = literalText("Museum Navigator")

            page(1) {
                placeholder(Slots.Border, itemStack(Material.GRAY_STAINED_GLASS_PANE) {
                    meta {
                        name = literalText("")
                    }
                })

                val seasonOneIcon = itemStack(Material.YELLOW_TERRACOTTA) {
                    meta {
                        name = literalText("Season 1") {
                            color = KColors.GOLD
                            italic = false
                        }
                        addLore {
                            +literalText("March 20th 2016 to August 4th 2017") {
                                color = TextColor.color(255, 224, 161)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.12.2 • 2.61GB • 925,603 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val seasonTwoIcon = itemStack(Material.GREEN_TERRACOTTA) {
                    meta {
                        name = literalText("Season 2") {
                            color = KColors.DARKGREEN
                            italic = false
                        }
                        addLore {
                            +literalText("August 4th 2017 to March 16th 2019") {
                                color = TextColor.color(167, 250, 167)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.13.2 • 2.59GB • 0 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val seasonThreeIcon = itemStack(Material.RED_TERRACOTTA) {
                    meta {
                        name = literalText("Season 3") {
                            color = KColors.DARKRED
                            italic = false
                        }
                        addLore {
                            +literalText("May 1st 2019 to March 18th 2020") {
                                color = TextColor.color(255, 184, 184)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.15.2 • 2.15GB • 0 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val hardcoreIcon = itemStack(Material.TOTEM_OF_UNDYING) {
                    meta {
                        name = literalText("Project Hardcore") {
                            color = KColors.DARKRED
                            italic = false
                        }
                        addLore {
                            +literalText("March 20th 2019 to April 24th 2019") {
                                color = TextColor.color(255, 184, 184)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.13.2 • 148MB • 0 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val keystoneIcon = itemStack(Material.TRIPWIRE_HOOK) {
                    meta {
                        name = literalText("Project Keystone") {
                            color = KColors.RED
                            italic = false
                        }
                        addLore {
                            +literalText("May 1st 2019 to June 27th 2020") {
                                color = TextColor.color(255, 184, 184)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.15.2 • 3.4GB • 0 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val amplifiedIcon = itemStack(Material.GOAT_HORN) {
                    meta {
                        name = literalText("Project Amplified") {
                            color = KColors.DARKGRAY
                            italic = false
                        }
                        addLore {
                            +literalText("December 1st 2021 to March 16th 2022") {
                                color = TextColor.color(196, 196, 196)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.17.1 • 951.6MB • 0 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                        addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE)
                    }
                }

                val createIcon = itemStack(Material.STONECUTTER) {
                    meta {
                        name = literalText("Project Create") {
                            color = TextColor.color(81, 87, 121)
                            italic = false
                        }
                        addLore {
                            +literalText("TBA") {
                                color = TextColor.color(179, 187, 230)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("x ɴᴏᴛ ᴀʀᴄʜɪᴠᴇᴅ x") {
                                color = KColors.GRAY
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("No Data Available") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val communityOneIcon = itemStack(Material.YELLOW_CONCRETE) {
                    meta {
                        name = literalText("Community Server 1") {
                            color = KColors.GOLD
                            italic = false
                        }
                        addLore {
                            +literalText("June 28th 2020 to December 1st 2021") {
                                color = TextColor.color(255, 224, 161)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("[ ᴄʟɪᴄᴋ ᴛᴏ ᴡᴀʀᴘ ]") {
                                color = KColors.AQUA
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("Minecraft 1.17.1 • 2.63GB • 0 Chunks") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                val communityTwoIcon = itemStack(Material.GREEN_CONCRETE) {
                    meta {
                        name = literalText("Community Server 2") {
                            color = KColors.DARKGREEN
                            italic = false
                        }
                        addLore {
                            +literalText("March 19th 2022 to Present") {
                                color = TextColor.color(167, 250, 167)
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("x ɴᴏᴛ ᴀʀᴄʜɪᴠᴇᴅ x") {
                                color = KColors.GRAY
                                italic = false
                            }
                            +literalText(" ")
                            +literalText("No Data Available") {
                                color = KColors.DARKGRAY
                                italic = false
                            }
                        }
                    }
                }

                button(Slots.RowThreeSlotTwo, seasonOneIcon) {
                    server.dispatchCommand(server.consoleSender, "tpp ${it.player.name} s1")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowThreeSlotThree, seasonTwoIcon) {
                    server.dispatchCommand(server.consoleSender, "tpp ${it.player.name} s2")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowThreeSlotFour, seasonThreeIcon) {
                    server.dispatchCommand(server.consoleSender, "tpp ${it.player.name} s3")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }

                button(Slots.RowTwoSlotThree, hardcoreIcon) {
                    server.dispatchCommand(server.consoleSender, "tpp ${it.player.name} hardcore")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowTwoSlotFour, keystoneIcon) {
                    server.dispatchCommand(server.consoleSender, "tpp ${it.player.name} keystone")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowTwoSlotFive, amplifiedIcon) {
                    server.dispatchCommand(server.consoleSender, "ttp ${it.player.name} amplified")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowTwoSlotSix, createIcon) {
                    it.player.playSound(Sound.sound(Key.key("item.shield.break"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
                }

                button(Slots.RowThreeSlotSix, communityOneIcon) {
                    server.dispatchCommand(server.consoleSender, "ttp ${it.player.name} cs1")
                    it.player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 0.3f, 1f), Sound.Emitter.self())
                }
                button(Slots.RowThreeSlotSeven, communityTwoIcon) {
                    it.player.playSound(Sound.sound(Key.key("item.shield.break"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
                }
            }
        }

        listen<PlayerJoinEvent> {
            it.player.inventory.setItem(0, navigator)
        }

        listen<InventoryClickEvent> {
            it.isCancelled = true
        }

        listen<PlayerInteractEvent> {
            if (it.item?.type == Material.RECOVERY_COMPASS) {
                it.player.playSound(Sound.sound(Key.key("block.end_portal_frame.fill"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
                it.player.openGUI(navigatorGui)
            }
        }

        listen<PlayerMoveEvent> {
            if (it.player.world.name == "world" && it.to.y <= -3.0) {
                it.player.velocity = Vector(it.player.velocity.x, 1.0, it.player.velocity.z)
                it.player.playSound(Sound.sound(Key.key("entity.player.attack.sweep"), Sound.Source.MASTER, 1f, 1f), Sound.Emitter.self())
            }
        }
    }

    override fun shutdown() { }
}