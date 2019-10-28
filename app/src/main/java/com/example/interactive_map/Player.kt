package com.example.interactive_map

class player_class {
    private var relax: Int = 15
    private var hungry: Int = 15
    //private var sleep: Int = 15
    private var rep: Int = 15
    private var money: Int = 150
    private var name: String = ""
    private var intellect: Int = 1
    //var apartment: Int = 1
    //var clothes: Int = 1

    init {
        relax=50
        //sleep=50
        hungry=50
        money=200
        rep=0
        //name=name1
    }
    fun set_relax(count : Int){
        if ((count>=0) && (count<101))
            relax = count
        return
    }
    fun set_hungry(count : Int){
        if ((count>=0) && (count<101))
            hungry = count
        return
    }
    fun set_rep(count : Int){
        if (count>=0)
            rep = count
        return
    }
    fun set_money(count : Int){
        if (count >=0)
            money = count
        return
    }
    fun set_name(str : String){
        name = str
        return
    }
    fun set_intellect(count : Int){
        intellect = count
        return
    }

    fun get_relax():Int{
        return relax
    }
    fun get_hungry():Int{
        return hungry
    }
    fun get_rep():Int{
        return rep
    }
    fun get_money():Int{
        return money
    }
    fun get_name():String{
        return name
    }
    fun get_intellect():Int{
        return intellect
    }
/////////////////////////////////
/////////////////////////////////
    fun relax1():Boolean {
        if (hungry > 10) {
            if (relax > 80)
                relax = 100
            else
                relax += 20
            hungry -= 10
            return true
        }else return false
    }
    fun relax2():Boolean {
        if (hungry > 20 && money > 15) {
            if (relax > 65) {
                relax = 100
            } else {
                relax += 35
            }
            hungry -= 20
            money -= 15
            return true
        }else return false
    }
    fun relax3():Boolean {
        if (money > 10) {
            if (relax > 77) {
                relax = 100
            } else {
                relax += 23
            }
            if (hungry > 95) {
                hungry = 100
            } else {
                hungry += 5
            }
            money -= 10
            return true
        }else return false
    }
    fun relax4():Boolean {
        if (hungry > 5 && money > 5) {
            if (relax > 80) {
                relax = 100
            } else {
                relax += 20
            }
            hungry -= 5
            money -= 5
            return true
        }else return false
    }
    fun relax5():Boolean {
        if (hungry > 3 && money > 4) {
            if (relax > 92) {
                relax = 100
            } else {
                relax += 8
            }
            hungry -= 3
            money -= 4
            return true
        }else return false
    }
    /////////////////////////////////
    fun eat1():Boolean {
        if (relax > 2 && money >= 4) {
            relax -= 2
            if (hungry > 94) {
                hungry = 100; } else {
                hungry += 6; }
            money -= 4
            return true
        }else return false
    }
    fun eat2():Boolean {
        if (relax > 8 && money >= 7){
            relax -= 8
            if (hungry > 94) {
                hungry = 100
            } else hungry += 10
            money -= 7
            return true
        }else return false
    }
    fun eat3():Boolean {
        if (relax>11 && money>=10) {
            relax -= 11
            if (hungry>75)
                hungry = 100
            else hungry +=25
            money -= 10
            return true
        }else return false
    }
    fun eat4():Boolean {
        if (relax>15 && money>=15) {
            relax -= 15
            if (hungry>70)
                hungry = 100
            else hungry += 30
            money -= 15
            return true
        }else return false
    }
    /////////////////////////////////
    fun work1():Boolean {
        if (relax>10 && hungry>7) {
            relax -= 10
            hungry -= 7
            money += 8
            return true
        }else return false
    }
    fun work2():Boolean {
        if (relax>15 && hungry>14) {
            relax -= 15
            hungry -= 14
            money += 13
            return true
        }else
            return false
    }
    fun work3():Boolean {
        if (relax>35 && hungry>8) {
            relax -= 35
            hungry -= 8
            money += 25
            return true
        }else return false
    }
    fun work4():Boolean {
        if (relax>15 && hungry>30) {
            relax -= 15
            hungry -= 30
            money += 3
            return true
        }else return false
    }
    fun worktrouble(){
        if (relax >= 5 && hungry >=4) {
            relax -= 5
            hungry -= 4
        }
        return
    }

/////////////////////////////
///     NEW_FUNCTION      ///
/////////////////////////////

    fun read_paper():Boolean {
        if (hungry>6 && money > 1) {
            if (relax > 96)
                relax = 100
            else relax += 4
            hungry -= 6
            money -= 1
            if ((0..20).random() == 1)
                intellect++
            return true
        }else return false
    }
    fun kek_on_seafront():Boolean {
        if (hungry>2) {
            if (relax > 96)
                relax = 100
            else relax += 4
            hungry -= 2
            return true
        }else return false
    }
    fun go_library():Boolean {
        if (relax>12 && hungry>6 && money > 1) {
            relax -= 12
            hungry -= 6
            money -= 1
            if ((0..2).random() == 1)
                intellect++
            return true
        }else return false
    }
    fun narodnik():Boolean{
        if (relax > 35 && hungry > 15 && money > 25) {
            relax -= 35
            hungry -= 15
            money -= 25
            rep += 5
            return true
        }else return false
    }
    fun theatre():Boolean{
        if (hungry > 3 && money > 9) {
            if (relax > 93) {
                relax = 100
            } else {
                relax += 7
            }
            hungry -= 3
            money -= 9
            return true
        }else return false
    }
    fun square():Boolean{
        if (hungry > 2) {
            if (relax > 98) {
                relax = 100
            } else {
                relax += 2
            }
            hungry -= 2
            return true
        }else return false
    }
    fun smoke():Boolean{
        if (hungry > 1 && money > 1) {
            if (relax > 98) {
                relax = 100
            } else {
                relax += 2
            }
            hungry -= 1
            money -= 1
            return true
        }else return false
    }


}