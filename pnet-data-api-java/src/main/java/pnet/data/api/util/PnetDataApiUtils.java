/* Copyright 2017 Porsche Informatik GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pnet.data.api.util;

import java.lang.reflect.Array;
import java.text.Collator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * Utilities for the pnet-data-api.
 *
 * @author ham
 */
public final class PnetDataApiUtils
{

    /**
     * A collator set to primary strength, which means 'a', 'A' and '&auml;' is the same
     */
    public static final Collator DICTIONARY_COLLATOR;

    public static final Comparator<String> DICTIONARY_COMPARATOR = (left, right) -> dictionaryCompare(left, right);

    static
    {
        DICTIONARY_COLLATOR = Collator.getInstance();

        DICTIONARY_COLLATOR.setStrength(Collator.PRIMARY);
        DICTIONARY_COLLATOR.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
    }

    private PnetDataApiUtils()
    {
        super();
    }

    /**
     * Nullsafe empty and blank check for strings
     *
     * @param s the string
     * @return true if empty
     */
    public static boolean isEmpty(String s)
    {
        return s == null || s.trim().length() == 0;
    }

    /**
     * Compares the two objects. If one of the objects is null, it will always be greater than the other object. If both
     * objects are null, they are equal.
     *
     * @param <TYPE> the type of the object
     * @param left the first object
     * @param right the second object
     * @return the result of the compare function
     */
    public static <TYPE extends Comparable<TYPE>> int compare(final TYPE left, final TYPE right)
    {
        if (left == null)
        {
            if (right != null)
            {
                return 1;
            }
        }
        else
        {
            if (right != null)
            {
                return left.compareTo(right);
            }

            return -1;
        }

        return 0;
    }

    /**
     * Compares the two objects. If one of the objects is null, it will always be greater than the other object. If both
     * objects are null, they are equal. Uses the comparator to compare the objects.
     *
     * @param <TYPE> the type of the object
     * @param comparator the comparator to be used
     * @param left the first object
     * @param right the second object
     * @return the result of the compare function
     */
    public static <TYPE> int compare(final Comparator<TYPE> comparator, final TYPE left, final TYPE right)
    {
        if (left == null)
        {
            if (right != null)
            {
                return 1;
            }
        }
        else
        {
            if (right != null)
            {
                return comparator.compare(left, right);
            }

            return -1;
        }

        return 0;
    }

    /**
     * Compares the strings using a dictionary collator. If one of the objects is null, it will always be greater than
     * the other object. If both objects are null, they are equal.
     *
     * @param left the first string
     * @param right the second string
     * @return the result of the compare function
     */
    public static int dictionaryCompare(final String left, final String right)
    {
        return compare(DICTIONARY_COLLATOR, left, right);
    }

    /**
     * Returns the text for the specified language.
     *
     * @param language the language, may be null
     * @param texts the texts, may be null
     * @return the text, null if not found
     */
    public static String getText(Locale language, Map<Locale, String> texts)
    {
        if (texts == null || texts.isEmpty())
        {
            return null;
        }

        while (true)
        {
            String text = texts.get(language);

            if (text != null)
            {
                return text;
            }

            if (language == null)
            {
                return null;
            }
            language = getParentLanguage(language);
        }
    }

    /**
     * Returns the ROOT locale if language is null
     *
     * @param language the language
     * @return the locale, never null
     */
    public static Locale ensureLanguage(Locale language)
    {
        return (language != null) ? language : Locale.ROOT;
    }

    /**
     * Returns the parent or null if there is no parent
     *
     * @param language the language, may be null
     * @return the parent, may be null
     */
    public static Locale getParentLanguage(Locale language)
    {
        if (language == null)
        {
            return null;
        }

        if (Locale.ROOT.equals(language))
        {
            return null;
        }

        if (!isEmpty(language.getVariant()))
        {
            return new Locale(language.getLanguage(), language.getCountry());
        }

        if (!StringUtils.isEmpty(language.getCountry()))
        {
            return new Locale(language.getLanguage());
        }

        if (!StringUtils.isEmpty(language.getLanguage()))
        {
            return Locale.ROOT;
        }

        return null;
    }

    public static <T> boolean contains(final T[] array, final T value)
    {
        if (value == null)
        {
            for (T item : array)
            {
                if (item == null)
                {
                    return true;
                }
            }
        }
        else
        {
            for (T item : array)
            {
                if (item == value || value.equals(item))
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Nullsafe empty function for arrays
     *
     * @param <T> the type of array items
     * @param array the array
     * @return true if null or empty
     */
    public static <T> boolean isEmpty(T[] array)
    {
        return (array == null) || (Array.getLength(array) <= 0);
    }

    public static <T> List<T> unmodifiable(List<T> list)
    {
        if (list == null)
        {
            return null;
        }

        if (list.getClass().getSimpleName().contains("Unmodifiable"))
        {
            return list;
        }

        return Collections.unmodifiableList(list);
    }

    /**
     * format a iso date and time.
     *
     * @param dateTime - the date and time to format
     * @return Formatted date
     */
    public static String formatISO(LocalDateTime dateTime)
    {
        if (dateTime == null)
        {
            return null;
        }

        return dateTime.format(DateTimeFormatter.ISO_DATE_TIME) + "Z";
    }

    /**
     * Parses a iso date.
     *
     * @param dateAsString - String
     * @return LocalDate
     */
    public static LocalDateTime parseISODateTime(String dateAsString)
    {
        if ((dateAsString == null) || (dateAsString.trim().length() == 0))
        {
            return null;
        }

        return LocalDateTime.parse(dateAsString, findFormatter(dateAsString));
    }

    private static DateTimeFormatter findFormatter(String dateAsString)
    {
        if (dateAsString.contains("T"))
        {
            return DateTimeFormatter.ISO_DATE_TIME;
        }

        return DateTimeFormatter.ISO_DATE;
    }
}