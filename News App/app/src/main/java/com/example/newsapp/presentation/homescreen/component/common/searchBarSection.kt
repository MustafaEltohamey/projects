@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.newsapp.presentation.homescreen.component.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.newsapp.R
import com.example.newsapp.utils.Dimens._12Dp

@Composable
fun SearchBarSection(
    modifier: Modifier = Modifier,
    text: TextFieldValue,
    readOnly: Boolean,
    onClick: (() -> Unit)? = null,
    onValueChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit = {}
) {

    Box (
        modifier =  modifier
            .padding(_12Dp)
            .fillMaxWidth()

    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = readOnly,
            placeholder = {
                Text(text = stringResource(id = R.string.search_hint_for_searchbar))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            shape = RoundedCornerShape(_12Dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions (
                onSearch = {
                    onSearch()
                }
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
        )
        if (readOnly) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        onClick?.invoke()
                    }
            )
        }
    }

}

