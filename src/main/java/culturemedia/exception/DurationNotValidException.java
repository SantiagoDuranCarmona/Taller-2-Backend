package culturemedia.exception;

import java.text.MessageFormat;

public class DurationNotValidException extends CultureMediaException
{
    public DurationNotValidException(String title, Double duration) {
        super( MessageFormat.format("Video {0} with duration {1} is not valid", title, duration) );
    }
}