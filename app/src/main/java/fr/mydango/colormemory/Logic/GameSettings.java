package fr.mydango.colormemory.Logic;

public class GameSettings {
    private String _modeName;
    private int _minCombi;
    private int _maxCombi;
    private int _lives;
    private double _scoreMultiplier;

     public String GetModeName()
     {
         return _modeName;
     }
     public int GetMinCombi()
     {
         return _minCombi;
     }
    public int GetMaxCombi()
    {
        return _maxCombi;
    }
    public int GetLives()
    {
        return _lives;
    }
    public double GetScoreMultiplier()
    {
        return _scoreMultiplier;
    }

    public GameSettings(String id)
    {
        if(id.equals("0"))
        {
            _modeName = "Easy mode";
            _minCombi = 1;
            _maxCombi = 7;
            _lives = 2;
            _scoreMultiplier = 1;
        }
        else if(id.equals("1"))
        {
            _modeName = "Hard mode";
            _minCombi = 3;
            _maxCombi = 10;
            _lives = 2;
            _scoreMultiplier = 1.5;
        }
        else if(id.equals("2"))
        {
            _modeName = "Expert mode";
            _minCombi = 4;
            _maxCombi = 12;
            _lives = 2;
            _scoreMultiplier = 2;
        }
        else
        {
            _modeName = "Chrono mode";
            _minCombi = 1;
            _maxCombi = 8;
            _lives = 3;
            _scoreMultiplier = 1.5;
        }
    }

}
